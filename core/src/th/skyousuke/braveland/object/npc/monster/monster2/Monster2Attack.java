package th.skyousuke.braveland.object.npc.monster.monster2;


import com.badlogic.gdx.graphics.g2d.Animation;

import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster2Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Monster2Attack() {
        super(Assets.instance.monster2Atlas, "monster2_attack", 5, Animation.PlayMode.NORMAL, 21, -42);

        setKeyFrameData(0, 62, 76, -10, 0);
        setKeyFrameData(1, 62, 76, -10, 0);
        setKeyFrameData(2, 62, 76, -10, 0);
        setKeyFrameData(3, 62, 76, -10, 0);
        setKeyFrameData(4, 62, 76, -10, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster2) {
        return BASE_FRAME_DURATION / monster2.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster2) {
        HotBox hotBoxFrame1 = new HotBox(25, 25, 65, 70, new Damage(1f * monster2.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame1.getTargets().addAll(monster2.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(55, 5, 30, 30, new Damage(0.25f * monster2.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(monster2.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(10, 8, 65, 13, new Damage(0.25f * monster2.getDamage(), 150f, ViewDirection.LEFT));
        hotBoxFrame3.getTargets().addAll(monster2.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster2) {
        monster2.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster2) {
        monster2.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster2) {
        monster2.getActionStateMachine().changeState(new Monster2Stand());
    }
}
