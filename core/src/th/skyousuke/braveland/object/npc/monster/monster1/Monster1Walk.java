package th.skyousuke.braveland.object.npc.monster.monster1;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster1Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Monster1Walk() {
        super(Assets.instance.monster1Atlas, "monster1_walk", 4, Animation.PlayMode.LOOP, 21, -42);

        setKeyFrameData(0, 62, 76, -10, 0);
        setKeyFrameData(1, 62, 76, -12, 0);
        setKeyFrameData(2, 62, 76, -10, 0);
        setKeyFrameData(3, 62, 76, -12, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster1) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster1.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster1) {
        if (CompareUtils.floatEquals(monster1.getVelocity().x, 0)) {
            monster1.getActionStateMachine().changeState(new Monster1Stand());
        }
    }
}
