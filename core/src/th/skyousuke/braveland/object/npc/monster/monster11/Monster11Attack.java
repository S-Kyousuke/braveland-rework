package th.skyousuke.braveland.object.npc.monster.monster11;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster11Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster11Attack() {
        super(Assets.instance.monster11Atlas, "monster11_attack", 10, Animation.PlayMode.NORMAL, 70, 0);

        setKeyFrameData(0, 70, 60, -11, 2);
        setKeyFrameData(1, 70, 60, -7, 4);
        setKeyFrameData(2, 70, 68, -12, 0);
        setKeyFrameData(3, 70, 68, -34, -16);
        setKeyFrameData(4, 70, 68, -39, -14);
        setKeyFrameData(5, 70, 68, -6, -7);
        setKeyFrameData(6, 70, 68, -6, 1);
        setKeyFrameData(7, 70, 65, -4, 0);
        setKeyFrameData(8, 70, 62, -11, 4);
        setKeyFrameData(9, 70, 60, -5, 2);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster11) {
        return BASE_FRAME_DURATION / monster11.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter monster11) {
        HotBox hotBoxFrame3 = new HotBox(65, 0, 73, 30, new Damage(1f * monster11.getDamage(), 100f, ViewDirection.RIGHT));
        hotBoxFrame3.setMaxTarget(3);
        hotBoxFrame3.getTargets().addAll(monster11.getEnemies());

        HotBox hotBoxFrame4 = new HotBox(75, 0, 75, 45, new Damage(0.8f * monster11.getDamage(), 100f, ViewDirection.RIGHT));
        hotBoxFrame4.setMaxTarget(3);
        hotBoxFrame4.getTargets().addAll(monster11.getEnemies());

        HotBox hotBoxFrame5 = new HotBox(75, 0, 105, 30, new Damage(0.6f * monster11.getDamage(), 100f, ViewDirection.RIGHT));
        hotBoxFrame5.setMaxTarget(3);
        hotBoxFrame5.getTargets().addAll(monster11.getEnemies());

        addHotBox(3, hotBoxFrame3);
        addHotBox(4, hotBoxFrame4);
        addHotBox(5, hotBoxFrame5);
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster11) {
        monster11.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster11) {
        monster11.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster11) {
        monster11.getActionStateMachine().changeState(new Monster11Stand());
    }
}
