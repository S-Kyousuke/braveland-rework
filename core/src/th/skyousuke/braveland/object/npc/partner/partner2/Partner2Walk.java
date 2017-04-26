package th.skyousuke.braveland.object.npc.partner.partner2;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner2Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner2Walk() {
        super(Assets.instance.partner2Atlas, "partner2_walk", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 66, -52, 0);
        setKeyFrameData(1, 35, 65, -54, 0);
        setKeyFrameData(2, 35, 65, -54, 0);
        setKeyFrameData(3, 35, 65, -57, -9);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner2) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / partner2.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner2) {
        if (!partner2.isOnGround()) {
            partner2.getActionStateMachine().changeState(new Partner2Jump());
        } else if (CompareUtils.floatEquals(partner2.getVelocity().x, 0)) {
            partner2.getActionStateMachine().changeState(new Partner2Stand());
        }
    }
}
