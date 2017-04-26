package th.skyousuke.braveland.object.npc.partner.partner3;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner3Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner3Walk() {
        super(Assets.instance.partner3Atlas, "partner3_walk", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 66, -42, -1);
        setKeyFrameData(1, 35, 65, -38, -2);
        setKeyFrameData(2, 35, 66, -42, -1);
        setKeyFrameData(3, 35, 65, -39, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner3) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / partner3.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner3) {
        if (!partner3.isOnGround()) {
            partner3.getActionStateMachine().changeState(new Partner3Jump());
        } else if (CompareUtils.floatEquals(partner3.getVelocity().x, 0)) {
            partner3.getActionStateMachine().changeState(new Partner3Stand());
        }
    }
}
