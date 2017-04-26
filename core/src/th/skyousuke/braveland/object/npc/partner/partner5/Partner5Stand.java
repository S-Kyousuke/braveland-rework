package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Partner5Stand extends AbstractActionState {

    public Partner5Stand() {
        super(Assets.instance.partner5Atlas, "partner5_stand", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 64, -8, -1);
        setKeyFrameData(1, 35, 65, -8, -1);
        setKeyFrameData(2, 35, 66, -8, -1);
        setKeyFrameData(3, 35, 65, -8, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter character) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner5) {
        if (!partner5.isOnGround()) {
            partner5.getActionStateMachine().changeState(new Partner5Jump());
        } else if (!CompareUtils.floatEquals(partner5.getVelocity().x, 0)) {
            partner5.getActionStateMachine().changeState(new Partner5Walk());
        }
    }
}
