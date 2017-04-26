package th.skyousuke.braveland.object.npc.monster.monster3;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster3Stand extends AbstractActionState {


    public Monster3Stand() {
        super(Assets.instance.monster3Atlas, "monster3_stand", 6, Animation.PlayMode.LOOP, 21, -25);

        setKeyFrameData(0, 47, 76, -20, 0);
        setKeyFrameData(1, 47, 76, -20, 0);
        setKeyFrameData(2, 47, 76, -20, 0);
        setKeyFrameData(3, 47, 76, -20, 0);
        setKeyFrameData(4, 47, 76, -20, 0);
        setKeyFrameData(5, 47, 76, -20, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster3) {
        return 0.15f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster3) {
        if (!CompareUtils.floatEquals(monster3.getVelocity().x, 0)) {
            monster3.getActionStateMachine().changeState(new Monster3Walk());
        }
    }
}
