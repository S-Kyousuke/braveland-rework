package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner5Jump extends AbstractActionState {

    public Partner5Jump() {
        super(Assets.instance.partner5Atlas, "partner5_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 69, -8, -1);
        setKeyFrameData(1, 35, 69, -8, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner5) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner5) {
        if (partner5.isOnGround()) {
            if (!CompareUtils.floatEquals(partner5.getVelocity().x, 0)) {
                partner5.getActionStateMachine().changeState(new Partner5Walk());
            } else {
                partner5.getActionStateMachine().changeState(new Partner5Stand());
            }
        }
    }
}
