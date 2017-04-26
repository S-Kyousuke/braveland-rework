package th.skyousuke.braveland.object.npc.monster.monster7;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster7Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster7Attack() {
        super(Assets.instance.monster7Atlas, "monster7_attack", 7, Animation.PlayMode.NORMAL, 85, 0);

        setKeyFrameData(0, 85, 70, -8, 0);
        setKeyFrameData(1, 85, 70, -7, 0);
        setKeyFrameData(2, 85, 70, -17, 0);
        setKeyFrameData(3, 85, 70, -21, 0);
        setKeyFrameData(4, 85, 70, -25, 0);
        setKeyFrameData(5, 85, 70, -6, -3);
        setKeyFrameData(6, 85, 70, -7, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster7) {
        return BASE_FRAME_DURATION / monster7.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster7) {
        HotBox hotBoxFrame5 = new HotBox(18, 0, 78, 75, new Damage(1f * monster7.getDamage(), 250f, ViewDirection.RIGHT));
        hotBoxFrame5.setMaxTarget(3);
        hotBoxFrame5.getTargets().addAll(monster7.getEnemies());

        addHotBox(5, hotBoxFrame5);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster7) {
        monster7.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster7) {
        monster7.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster7) {
        monster7.getActionStateMachine().changeState(new Monster7Stand());
    }
}
