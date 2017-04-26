package th.skyousuke.braveland.object.npc.monster.monster8;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster8Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster8Walk() {
        super(Assets.instance.monster8Atlas, "monster8_walk", 6, Animation.PlayMode.LOOP, 26, -32);

        setKeyFrameData(0, 58, 78, -24, 0);
        setKeyFrameData(1, 58, 78, -18, 0);
        setKeyFrameData(2, 58, 78, -17, 3);
        setKeyFrameData(3, 58, 78, -24, 0);
        setKeyFrameData(4, 58, 78, -17, 0);
        setKeyFrameData(5, 58, 78, -15, 3);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster8) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster8.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster8) {
        if (CompareUtils.floatEquals(monster8.getVelocity().x, 0)) {
            monster8.getActionStateMachine().changeState(new Monster8Stand());
        }
    }
}
