package th.skyousuke.braveland.object.npc.monster.monster12;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster12Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;
    private static final float DASH_SPEED = 300f;

    public Monster12Attack() {
        super(Assets.instance.monster12Atlas, "monster12_attack", 13, Animation.PlayMode.NORMAL, 80, 0);

        setKeyFrameData(0, 80, 120, -63, 0);
        setKeyFrameData(1, 80, 120, -83, -1);
        setKeyFrameData(2, 80, 120, -86, -5);
        setKeyFrameData(3, 80, 120, -84, -7);
        setKeyFrameData(4, 80, 120, -85, -8);
        setKeyFrameData(5, 80, 120, -73, -8);
        setKeyFrameData(6, 80, 120, -75, -9);
        setKeyFrameData(7, 80, 120, -74, -9);
        setKeyFrameData(8, 80, 120, -74, -10);
        setKeyFrameData(9, 80, 120, -93, -13);
        setKeyFrameData(10, 80, 120, -93, -22);
        setKeyFrameData(11, 80, 120, -86, -12);
        setKeyFrameData(12, 80, 120, -63, -11);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster12) {
        return BASE_FRAME_DURATION / monster12.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster12) {
        HotBox hotBoxFrame9 = new HotBox(0, 0, 95, 150, new Damage(1f * monster12.getDamage(), 300f, ViewDirection.RIGHT));
        hotBoxFrame9.getTargets().addAll(monster12.getEnemies());

        HotBox hotBoxFrame10 = new HotBox(0, 0, 95, 150, new Damage(0.8f * monster12.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame10.getTargets().addAll(monster12.getEnemies());

        addHotBox(9, hotBoxFrame9);
        addHotBox(10, hotBoxFrame10);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster12) {
        monster12.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster12) {
        monster12.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster12) {
        monster12.getActionStateMachine().changeState(new Monster12Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter monster12, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 9) {
            monster12.getVelocity().x = (monster12.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * DASH_SPEED;
        }
    }
}
