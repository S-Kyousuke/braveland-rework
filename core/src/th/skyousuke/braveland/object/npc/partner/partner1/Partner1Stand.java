package th.skyousuke.braveland.object.npc.partner.partner1;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Partner1Stand extends AbstractActionState {

    public Partner1Stand() {
        super(Assets.instance.partner1Atlas, "partner1_stand", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 65, -7, -2);
        setKeyFrameData(1, 35, 63, -7, -1);
        setKeyFrameData(2, 35, 64, -7, 0);
        setKeyFrameData(3, 35, 63, -7, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner1) {
        return 0.5f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner1) {
        if (!partner1.isOnGround()) {
            partner1.getActionStateMachine().changeState(new Partner1Jump());
        } else if (!CompareUtils.floatEquals(partner1.getVelocity().x, 0)) {
            partner1.getActionStateMachine().changeState(new Partner1Walk());
        }
    }
}
