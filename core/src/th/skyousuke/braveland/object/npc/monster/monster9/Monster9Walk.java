package th.skyousuke.braveland.object.npc.monster.monster9;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster9Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.2f;

    public Monster9Walk() {
        super(Assets.instance.monster9Atlas, "monster9_walk", 4, Animation.PlayMode.LOOP, 65);

        setKeyFrameData(0, 65, 120, -40, 0);
        setKeyFrameData(1, 65, 120, -36, 0);
        setKeyFrameData(2, 65, 120, -40, 0);
        setKeyFrameData(3, 65, 120, -36, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster9) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster9.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster9) {
        if (CompareUtils.floatEquals(monster9.getVelocity().x, 0)) {
            monster9.getActionStateMachine().changeState(new Monster9Stand());
        }
    }
}
