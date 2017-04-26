package th.skyousuke.braveland.object.npc.monster.monster6;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster6Stand extends AbstractActionState {

    public Monster6Stand() {
        super(Assets.instance.monster6Atlas, "monster6_stand", 3, Animation.PlayMode.LOOP, 31, -53);

        setKeyFrameData(0, 84, 100, -10, 0);
        setKeyFrameData(1, 84, 100, -10, 0);
        setKeyFrameData(2, 84, 100, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster6) {
        return 0.2f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster6) {
        if (!CompareUtils.floatEquals(monster6.getVelocity().x, 0)) {
            monster6.getActionStateMachine().changeState(new Monster6Walk());
        }
    }
}
