package th.skyousuke.braveland.object.npc.monster.monster10;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster10Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster10Attack() {
        super(Assets.instance.monster10Atlas, "monster10_attack", 6, Animation.PlayMode.NORMAL, 65);

        setKeyFrameData(0, 65, 120, -40, 0);
        setKeyFrameData(1, 65, 120, -32, 0);
        setKeyFrameData(2, 65, 120, -74, 0);
        setKeyFrameData(3, 65, 120, -74, -3);
        setKeyFrameData(4, 65, 120, -74, -19);
        setKeyFrameData(5, 65, 120, -73, -28);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster10) {
        return BASE_FRAME_DURATION / monster10.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster10) {
        HotBox hotBoxFrame2 = new HotBox(71, 12, 82, 99, new Damage(1f * monster10.getDamage(), 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(monster10.getEnemies());

        addHotBox(2, hotBoxFrame2);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster10) {
        monster10.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster10) {
        monster10.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster10) {
        monster10.getActionStateMachine().changeState(new Monster10Stand());
    }
}
