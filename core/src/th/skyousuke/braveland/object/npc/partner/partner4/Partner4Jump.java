package th.skyousuke.braveland.object.npc.partner.partner4;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner4Jump extends AbstractActionState {

    public Partner4Jump() {
        super(Assets.instance.partner4Atlas, "partner4_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 69, -16, -5);
        setKeyFrameData(1, 35, 69, -16, -4);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner4) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner4) {
        if (partner4.isOnGround()) {
            if (!CompareUtils.floatEquals(partner4.getVelocity().x, 0)) {
                partner4.getActionStateMachine().changeState(new Partner4Walk());
            } else {
                partner4.getActionStateMachine().changeState(new Partner4Stand());
            }
        }
    }
}
