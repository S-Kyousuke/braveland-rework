package th.skyousuke.braveland.object.npc.monster.monster1;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster1Stand extends AbstractActionState {

    public Monster1Stand() {
        super(Assets.instance.monster1Atlas, "monster1_stand", 4, Animation.PlayMode.NORMAL, 21, -42);

        setKeyFrameData(0, 62, 76, -10, 0);
        setKeyFrameData(1, 62, 76, -10, 0);
        setKeyFrameData(2, 62, 76, -10, 0);
        setKeyFrameData(3, 62, 76, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster1) {
        return 0.3f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster1) {
        if (!CompareUtils.floatEquals(monster1.getVelocity().x, 0)) {
            monster1.getActionStateMachine().changeState(new Monster1Walk());
        }
    }
}
