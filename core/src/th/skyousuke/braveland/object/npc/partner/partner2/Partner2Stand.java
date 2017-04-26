package th.skyousuke.braveland.object.npc.partner.partner2;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Partner2Stand extends AbstractActionState {

    public Partner2Stand() {
        super(Assets.instance.partner2Atlas, "partner2_stand", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 64, -49, 0);
        setKeyFrameData(1, 35, 65, -48, 0);
        setKeyFrameData(2, 35, 66, -50, 0);
        setKeyFrameData(3, 35, 65, -48, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner2) {
        return 0.5f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter partner2) {
        if (!partner2.isOnGround()) {
            partner2.getActionStateMachine().changeState(new Partner2Jump());
        } else if (!CompareUtils.floatEquals(partner2.getVelocity().x, 0)) {
            partner2.getActionStateMachine().changeState(new Partner2Walk());
        }
    }
}
