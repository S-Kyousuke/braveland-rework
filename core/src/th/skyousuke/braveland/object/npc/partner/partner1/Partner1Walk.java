package th.skyousuke.braveland.object.npc.partner.partner1;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner1Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner1Walk() {
        super(Assets.instance.partner1Atlas, "partner1_walk", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 62, -8, 0);
        setKeyFrameData(1, 35, 62, -8, 0);
        setKeyFrameData(2, 35, 62, -8, 0);
        setKeyFrameData(3, 35, 62, -8, -2);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner1) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / partner1.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner1) {
        if (!partner1.isOnGround()) {
            partner1.getActionStateMachine().changeState(new Partner1Jump());
        } else if (CompareUtils.floatEquals(partner1.getVelocity().x, 0)) {
            partner1.getActionStateMachine().changeState(new Partner1Stand());
        }
    }
}
