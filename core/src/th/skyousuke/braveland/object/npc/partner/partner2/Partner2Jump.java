package th.skyousuke.braveland.object.npc.partner.partner2;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner2Jump extends AbstractActionState {

    public Partner2Jump() {
        super(Assets.instance.partner2Atlas, "partner2_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 69, -47, 0);
        setKeyFrameData(1, 35, 69, -49, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner2) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner2) {
        if (partner2.isOnGround()) {
            if (!CompareUtils.floatEquals(partner2.getVelocity().x, 0)) {
                partner2.getActionStateMachine().changeState(new Partner2Walk());
            } else {
                partner2.getActionStateMachine().changeState(new Partner2Stand());
            }
        }
    }
}
