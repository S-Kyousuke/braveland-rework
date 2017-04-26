package th.skyousuke.braveland.object.npc.monster.monster10;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class Monster10Stand extends AbstractActionState {

    public Monster10Stand() {
        super(Assets.instance.monster10Atlas, "monster10_stand", 6, Animation.PlayMode.LOOP, 65);

        setKeyFrameData(0, 65, 120, -40, 0);
        setKeyFrameData(1, 65, 120, -32, 0);
        setKeyFrameData(2, 65, 120, -32, 0);
        setKeyFrameData(3, 65, 120, -35, 0);
        setKeyFrameData(4, 65, 120, -42, 0);
        setKeyFrameData(5, 65, 120, -41, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster10) {
        return 0.2f;
    }

    @Override
    protected void onActionUpdate(AbstractCharacter monster10) {
        if (!CompareUtils.floatEquals(monster10.getVelocity().x, 0)) {
            monster10.getActionStateMachine().changeState(new Monster10Walk());
        }
    }
}
