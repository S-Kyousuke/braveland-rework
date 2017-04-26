package th.skyousuke.braveland.object.npc.monster.monster11;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster11Stand extends AbstractActionState {

    public Monster11Stand() {
        super(Assets.instance.monster11Atlas, "monster11_stand", 8, Animation.PlayMode.LOOP, 70, 0);

        setKeyFrameData(0, 70, 60, -5, 1);
        setKeyFrameData(1, 70, 60, -11, 2);
        setKeyFrameData(2, 70, 60, -6, 4);
        setKeyFrameData(3, 70, 60, -6, 4);
        setKeyFrameData(4, 70, 60, -4, 2);
        setKeyFrameData(5, 70, 60, -11, 2);
        setKeyFrameData(6, 70, 60, -6, 4);
        setKeyFrameData(7, 70, 60, -7, 3);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster11) {
        return 0.25f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster11) {
        if (!CompareUtils.floatEquals(monster11.getVelocity().x, 0)) {
            monster11.getActionStateMachine().changeState(new Monster11Walk());
        }
    }
}
