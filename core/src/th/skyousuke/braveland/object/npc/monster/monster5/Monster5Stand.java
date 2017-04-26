package th.skyousuke.braveland.object.npc.monster.monster5;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster5Stand extends AbstractActionState {

    public Monster5Stand() {
        super(Assets.instance.monster5Atlas, "monster5_stand", 3, Animation.PlayMode.LOOP, 31, -53);

        setKeyFrameData(0, 84, 100, -10, 0);
        setKeyFrameData(1, 84, 100, -10, 0);
        setKeyFrameData(2, 84, 100, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster5) {
        return 1f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster5) {
        if (!CompareUtils.floatEquals(monster5.getVelocity().x, 0)) {
            monster5.getActionStateMachine().changeState(new Monster5Walk());
        }
    }
}
