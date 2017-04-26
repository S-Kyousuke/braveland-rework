package th.skyousuke.braveland.object.npc.monster.monster11;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster11Walk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.14f;

    public Monster11Walk() {
        super(Assets.instance.monster11Atlas, "monster11_walk", 4, Animation.PlayMode.LOOP, 70, 0);

        setKeyFrameData(0, 70, 60, -6, -8);
        setKeyFrameData(1, 70, 60, -10, -8);
        setKeyFrameData(2, 70, 60, -4, -8);
        setKeyFrameData(3, 70, 60, -7, -8);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster11) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / monster11.getMovementSpeed();
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster11) {
        if (CompareUtils.floatEquals(monster11.getVelocity().x, 0)) {
            monster11.getActionStateMachine().changeState(new Monster11Stand());
        }
    }
}
