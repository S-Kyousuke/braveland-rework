package th.skyousuke.braveland.object.npc.partner.partner3;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner3Jump extends AbstractActionState {

    public Partner3Jump() {
        super(Assets.instance.partner3Atlas, "partner3_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 69, -39, -17);
        setKeyFrameData(1, 35, 69, -39, -17);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner3) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner3) {
        if (partner3.isOnGround()) {
            if (!CompareUtils.floatEquals(partner3.getVelocity().x, 0)) {
                partner3.getActionStateMachine().changeState(new Partner3Walk());
            } else {
                partner3.getActionStateMachine().changeState(new Partner3Stand());
            }
        }
    }
}
