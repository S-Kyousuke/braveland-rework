package th.skyousuke.braveland.object.npc.monster.monster5;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster5Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster5Attack() {
        super(Assets.instance.monster5Atlas, "monster5_attack", 6, Animation.PlayMode.NORMAL, 31, -53);

        setKeyFrameData(0, 84, 100, -2, -4);
        setKeyFrameData(1, 84, 100, -2, -1);
        setKeyFrameData(2, 84, 100, -2, 0);
        setKeyFrameData(3, 84, 100, -2, 0);
        setKeyFrameData(4, 84, 100, -2, -2);
        setKeyFrameData(5, 84, 100, -2, -1);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster5) {
        return BASE_FRAME_DURATION / monster5.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster5) {
        HotBox hotBoxFrame4 = new HotBox(65, 0, 105, 100, new Damage(1f * monster5.getDamage(), 250f, ViewDirection.RIGHT));
        hotBoxFrame4.getTargets().addAll(monster5.getEnemies());
        addHotBox(4, hotBoxFrame4);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster5) {
        monster5.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster5) {
        monster5.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster5) {
        monster5.getActionStateMachine().changeState(new Monster5Stand());
    }
}
