package th.skyousuke.braveland.object.npc.monster.monster12;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster12Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.2f;

    public Monster12Walk() {
        super(Assets.instance.monster12Atlas, "monster12_walk", 8, Animation.PlayMode.LOOP, 80, 0);

        setKeyFrameData(0, 80, 120, -63, 0);
        setKeyFrameData(1, 80, 120, -67, 0);
        setKeyFrameData(2, 80, 120, -68, 0);
        setKeyFrameData(3, 80, 120, -63, 0);
        setKeyFrameData(4, 80, 120, -60, 0);
        setKeyFrameData(5, 80, 120, -64, 0);
        setKeyFrameData(6, 80, 120, -67, 0);
        setKeyFrameData(7, 80, 120, -60, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster12) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster12.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster12) {
        if (CompareUtils.floatEquals(monster12.getVelocity().x, 0)) {
            monster12.getActionStateMachine().changeState(new Monster12Stand());
        }
    }
}
