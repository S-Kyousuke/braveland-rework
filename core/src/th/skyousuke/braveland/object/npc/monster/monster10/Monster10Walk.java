package th.skyousuke.braveland.object.npc.monster.monster10;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster10Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.2f;

    public Monster10Walk() {
        super(Assets.instance.monster10Atlas, "monster10_walk", 4, Animation.PlayMode.LOOP, 65);

        setKeyFrameData(0, 65, 120, -40, 0);
        setKeyFrameData(1, 65, 120, -36, 0);
        setKeyFrameData(2, 65, 120, -40, 0);
        setKeyFrameData(3, 65, 120, -36, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster10) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster10.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster10) {
        if (CompareUtils.floatEquals(monster10.getVelocity().x, 0)) {
            monster10.getActionStateMachine().changeState(new Monster10Stand());
        }
    }
}
