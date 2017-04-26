package th.skyousuke.braveland.object.npc.monster.monster1;


import com.badlogic.gdx.graphics.g2d.Animation;

import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster1Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Monster1Attack() {
        super(Assets.instance.monster1Atlas, "monster1_atk", 5, Animation.PlayMode.NORMAL, 21, -42);

        setKeyFrameData(0, 62, 76, -10, 0);
        setKeyFrameData(1, 62, 76, -10, 0);
        setKeyFrameData(2, 62, 76, -10, 0);
        setKeyFrameData(3, 62, 76, -10, 0);
        setKeyFrameData(4, 62, 76, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster1) {
        return BASE_FRAME_DURATION / monster1.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster1) {
        HotBox hotBoxFrame1 = new HotBox(25, 25, 65, 70, new Damage(1f * monster1.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame1.getTargets().addAll(monster1.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(55, 5, 30, 30, new Damage(0.25f * monster1.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(monster1.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(10, 8, 65, 13, new Damage(0.25f * monster1.getDamage(), 150f, ViewDirection.LEFT));
        hotBoxFrame3.getTargets().addAll(monster1.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster1) {
        monster1.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster1) {
        monster1.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster1) {
        monster1.getActionStateMachine().changeState(new Monster1Stand());
    }
}
