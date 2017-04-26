package th.skyousuke.braveland.object.npc.monster.monster8;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster8Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.14f;

    public Monster8Attack() {
        super(Assets.instance.monster8Atlas, "monster8_attack", 5, Animation.PlayMode.NORMAL, 26, -32);

        setKeyFrameData(0, 58, 78, -21, 0);
        setKeyFrameData(1, 58, 78, -22, 0);
        setKeyFrameData(2, 58, 78, -22, 0);
        setKeyFrameData(3, 58, 78, -22, 0);
        setKeyFrameData(4, 58, 78, -25, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster8) {
        return BASE_FRAME_DURATION / monster8.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster8) {
        HotBox hotBoxFrame2 = new HotBox(61, 22, 27, 46, new Damage(1f * monster8.getDamage(), 100f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(3);
        hotBoxFrame2.getTargets().addAll(monster8.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(63, 16, 35, 57, new Damage(0.5f * monster8.getDamage(), 50f, ViewDirection.RIGHT));
        hotBoxFrame3.setMaxTarget(3);
        hotBoxFrame3.getTargets().addAll(monster8.getEnemies());

        addHotBox(2, hotBoxFrame2);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster8) {
        monster8.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster8) {
        monster8.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster8) {
        monster8.getActionStateMachine().changeState(new Monster8Stand());
    }
}
