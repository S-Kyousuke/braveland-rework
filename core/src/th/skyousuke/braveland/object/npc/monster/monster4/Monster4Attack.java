package th.skyousuke.braveland.object.npc.monster.monster4;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster4Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster4Attack() {
        super(Assets.instance.monster4Atlas, "monster4_attack", 5, Animation.PlayMode.NORMAL, 21, -22);

        setKeyFrameData(0, 43, 76, -10, 0);
        setKeyFrameData(1, 43, 76, -16, 0);
        setKeyFrameData(2, 43, 76, -33, 0);
        setKeyFrameData(3, 43, 76, -9, -10);
        setKeyFrameData(4, 43, 76, -10, -7);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster4) {
        return BASE_FRAME_DURATION / monster4.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster4) {
        HotBox hotBoxFrame2 = new HotBox(0, 5, 80, 75, new Damage(1f * monster4.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(monster4.getEnemies());

        addHotBox(2, hotBoxFrame2);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster4) {
        monster4.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster4) {
        monster4.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster4) {
        monster4.getActionStateMachine().changeState(new Monster4Stand());
    }
}
