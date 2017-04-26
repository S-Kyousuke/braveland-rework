package th.skyousuke.braveland.object.npc.monster.monster7;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster7Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.10f;

    public Monster7Walk() {
        super(Assets.instance.monster7Atlas, "monster7_walk", 8, Animation.PlayMode.LOOP, 85, 0);

        setKeyFrameData(0, 85, 70, -10, 0);
        setKeyFrameData(1, 85, 70, -14, 0);
        setKeyFrameData(2, 85, 70, -15, 0);
        setKeyFrameData(3, 85, 70, -15, 0);
        setKeyFrameData(4, 85, 70, -6, 0);
        setKeyFrameData(5, 85, 70, -2, 0);
        setKeyFrameData(6, 85, 70, -5, 0);
        setKeyFrameData(7, 85, 70, -9, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster7) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster7.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster7) {
        if (CompareUtils.floatEquals(monster7.getVelocity().x, 0)) {
            monster7.getActionStateMachine().changeState(new Monster7Stand());
        }
    }
}
