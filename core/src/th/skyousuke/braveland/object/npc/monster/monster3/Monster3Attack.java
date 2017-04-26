package th.skyousuke.braveland.object.npc.monster.monster3;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Monster3Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.15f;

    public Monster3Attack() {
        super(Assets.instance.monster3Atlas, "monster3_attack", 6, Animation.PlayMode.NORMAL, 21, -25);

        setKeyFrameData(0, 47, 76, -17, 0);
        setKeyFrameData(1, 47, 76, -17, 0);
        setKeyFrameData(2, 47, 76, -17, 0);
        setKeyFrameData(3, 47, 76, -17, 0);
        setKeyFrameData(4, 47, 76, -19, -22);
        setKeyFrameData(5, 47, 76, -20, -23);
    }

    @Override
    protected void initHotBox(AbstractCharacter monster3) {
        HotBox hotBoxFrame4 = new HotBox(55, -15, 151, 110, new Damage(1f * monster3.getDamage()));
        hotBoxFrame4.getTargets().addAll(monster3.getEnemies());

        addHotBox(4, hotBoxFrame4);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter monster3) {
        return BASE_FRAME_DURATION / monster3.getAttackSpeed();
    }

    @Override
    protected void onActionEnter(AbstractCharacter monster3) {
        monster3.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter monster3) {
        monster3.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter monster3) {
        monster3.getActionStateMachine().changeState(new Monster3Stand());
    }
}
