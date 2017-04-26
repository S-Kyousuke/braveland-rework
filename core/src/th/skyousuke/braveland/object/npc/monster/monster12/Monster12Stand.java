package th.skyousuke.braveland.object.npc.monster.monster12;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster12Stand extends AbstractActionState {

    public Monster12Stand() {
        super(Assets.instance.monster12Atlas, "monster12_stand", 6, Animation.PlayMode.LOOP, 80, 0);

        setKeyFrameData(0, 80, 120, -64, 0);
        setKeyFrameData(1, 80, 120, -68, 0);
        setKeyFrameData(2, 80, 120, -69, 0);
        setKeyFrameData(3, 80, 120, -64, 0);
        setKeyFrameData(4, 80, 120, -61, 0);
        setKeyFrameData(5, 80, 120, -65, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster12) {
        return 0.18f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster12) {
        if (!CompareUtils.floatEquals(monster12.getVelocity().x, 0)) {
            monster12.getActionStateMachine().changeState(new Monster12Walk());
        }
    }
}
