package th.skyousuke.braveland.object.npc.partner.partner4;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Partner4Stand extends AbstractActionState {

    public Partner4Stand() {
        super(Assets.instance.partner4Atlas, "partner4_stand", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 64, -16, -5);
        setKeyFrameData(1, 35, 65, -16, -5);
        setKeyFrameData(2, 35, 66, -16, -5);
        setKeyFrameData(3, 35, 65, -16, -5);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner4) {
        return 0.5f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner4) {
        if (!partner4.isOnGround()) {
            partner4.getActionStateMachine().changeState(new Partner4Jump());
        } else if (!CompareUtils.floatEquals(partner4.getVelocity().x, 0)) {
            partner4.getActionStateMachine().changeState(new Partner4Walk());
        }
    }
}
