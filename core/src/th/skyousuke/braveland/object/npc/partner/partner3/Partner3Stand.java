package th.skyousuke.braveland.object.npc.partner.partner3;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Partner3Stand extends AbstractActionState {

    public Partner3Stand() {
        super(Assets.instance.partner3Atlas, "partner3_stand", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 66, -40, -3);
        setKeyFrameData(1, 35, 66, -41, -3);
        setKeyFrameData(2, 35, 66, -42, -3);
        setKeyFrameData(3, 35, 66, -41, -3);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner3) {
        return 0.5f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner3) {
        if (!partner3.isOnGround()) {
            partner3.getActionStateMachine().changeState(new Partner3Jump());
        } else if (!CompareUtils.floatEquals(partner3.getVelocity().x, 0)) {
            partner3.getActionStateMachine().changeState(new Partner3Walk());
        }
    }
}
