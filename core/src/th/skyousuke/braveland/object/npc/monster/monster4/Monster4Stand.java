package th.skyousuke.braveland.object.npc.monster.monster4;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster4Stand extends AbstractActionState {

    public Monster4Stand() {
        super(Assets.instance.monster4Atlas, "monster4_stand", 4, Animation.PlayMode.LOOP, 21, -22);

        setKeyFrameData(0, 43, 76, -10, 0);
        setKeyFrameData(1, 43, 76, -10, 0);
        setKeyFrameData(2, 43, 76, -10, 0);
        setKeyFrameData(3, 43, 76, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster4) {
        return 0.2f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster4) {
        if (!CompareUtils.floatEquals(monster4.getVelocity().x, 0)) {
            monster4.getActionStateMachine().changeState(new Monster4Walk());
        }
    }
}
