package th.skyousuke.braveland.object.npc.partner.partner1;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;


public class Partner1Jump extends AbstractActionState {

    public Partner1Jump() {
        super(Assets.instance.partner1Atlas, "partner1_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 67, -8, 0);
        setKeyFrameData(1, 35, 67, -8, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner1) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner1) {
        if (partner1.isOnGround()) {
            if (!CompareUtils.floatEquals(partner1.getVelocity().x, 0)) {
                partner1.getActionStateMachine().changeState(new Partner1Walk());
            } else {
                partner1.getActionStateMachine().changeState(new Partner1Stand());
            }
        }
    }
}
