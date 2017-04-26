package th.skyousuke.braveland.object.npc.monster.monster2;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster2Stand extends AbstractActionState {

    public Monster2Stand() {
        super(Assets.instance.monster2Atlas, "monster2_stand", 4, Animation.PlayMode.LOOP, 21, -42);

        setKeyFrameData(0, 61, 76, -10, 0);
        setKeyFrameData(1, 61, 76, -10, 0);
        setKeyFrameData(2, 61, 76, -10, 0);
        setKeyFrameData(3, 61, 76, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster2) {
        return 0.3f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster2) {
        if (!CompareUtils.floatEquals(monster2.getVelocity().x, 0)) {
            monster2.getActionStateMachine().changeState(new Monster2Walk());
        }
    }
}
