package th.skyousuke.braveland.object.npc.monster.monster8;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster8Stand extends AbstractActionState {

    public Monster8Stand() {
        super(Assets.instance.monster8Atlas, "monster8_stand", 6, Animation.PlayMode.LOOP, 26, -32);

        setKeyFrameData(0, 58, 78, -11, 0);
        setKeyFrameData(1, 58, 78, -13, 0);
        setKeyFrameData(2, 58, 78, -19, 0);
        setKeyFrameData(3, 58, 78, -19, 0);
        setKeyFrameData(4, 58, 78, -17, 0);
        setKeyFrameData(5, 58, 78, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster8) {
        return 0.13f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster8) {
        if (!CompareUtils.floatEquals(monster8.getVelocity().x, 0)) {
            monster8.getActionStateMachine().changeState(new Monster8Walk());
        }
    }
}
