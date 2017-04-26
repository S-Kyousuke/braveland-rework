package th.skyousuke.braveland.object.npc.monster.monster7;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster7Stand extends AbstractActionState {

    public Monster7Stand() {
        super(Assets.instance.monster7Atlas, "monster7_stand", 4, Animation.PlayMode.LOOP, 85, 0);

        setKeyFrameData(0, 85, 70, -8, 0);
        setKeyFrameData(1, 85, 70, -13, 0);
        setKeyFrameData(2, 85, 70, -8, 0);
        setKeyFrameData(3, 85, 70, -6, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster7) {
        return 0.12f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster7) {
        if (!CompareUtils.floatEquals(monster7.getVelocity().x, 0)) {
            monster7.getActionStateMachine().changeState(new Monster7Walk());
        }
    }
}
