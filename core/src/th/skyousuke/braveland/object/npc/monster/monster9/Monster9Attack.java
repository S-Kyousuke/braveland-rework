package th.skyousuke.braveland.object.npc.monster.monster9;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster9Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster9Attack() {
        super(Assets.instance.monster9Atlas, "monster9_attack", 6, Animation.PlayMode.NORMAL, 65);

        setKeyFrameData(0, 65, 120, -40, 0);
        setKeyFrameData(1, 65, 120, -32, 0);
        setKeyFrameData(2, 65, 120, -98, -7);
        setKeyFrameData(3, 65, 120, -98, -8);
        setKeyFrameData(4, 65, 120, -98, -19);
        setKeyFrameData(5, 65, 120, -98, -28);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster9) {
        return BASE_FRAME_DURATION / monster9.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster9) {
        HotBox hotBoxFrame2 = new HotBox(71, 12, 82, 99, new Damage(1f * monster9.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(monster9.getEnemies());

        addHotBox(2, hotBoxFrame2);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster9) {
        monster9.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster9) {
        monster9.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster9) {
        monster9.getActionStateMachine().changeState(new Monster9Stand());
    }
}
