package th.skyousuke.braveland.object.npc.monster.monster6;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster6Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster6Attack() {
        super(Assets.instance.monster6Atlas, "monster6_attack", 6, Animation.PlayMode.NORMAL, 31, -53);

        setKeyFrameData(0, 84, 100, -2, -1);
        setKeyFrameData(1, 84, 100, -2, 0);
        setKeyFrameData(2, 84, 100, -2, 0);
        setKeyFrameData(3, 84, 100, -2, -2);
        setKeyFrameData(4, 84, 100, -2, -1);
        setKeyFrameData(5, 84, 100, -2, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster6) {
        return BASE_FRAME_DURATION / monster6.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster6) {
        HotBox hotBoxFrame3 = new HotBox(65, 0, 105, 100, new Damage(1f * monster6.getDamage(), 250f, ViewDirection.RIGHT));
        hotBoxFrame3.getTargets().addAll(monster6.getEnemies());
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster6) {
        monster6.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster6) {
        monster6.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster6) {
        monster6.getActionStateMachine().changeState(new Monster6Stand());
    }
}
