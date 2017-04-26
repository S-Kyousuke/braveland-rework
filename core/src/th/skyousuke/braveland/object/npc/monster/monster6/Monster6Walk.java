package th.skyousuke.braveland.object.npc.monster.monster6;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster6Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.2f;

    public Monster6Walk() {
        super(Assets.instance.monster6Atlas, "monster6_walk", 4, Animation.PlayMode.LOOP, 37, -53);

        setKeyFrameData(0, 90, 100, -10, 0);
        setKeyFrameData(1, 90, 100, -18, 0);
        setKeyFrameData(2, 90, 100, -6, 0);
        setKeyFrameData(3, 90, 100, -27, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster6) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster6.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster6) {
        if (CompareUtils.floatEquals(monster6.getVelocity().x, 0)) {
            monster6.getActionStateMachine().changeState(new Monster6Stand());
        }
    }
}
