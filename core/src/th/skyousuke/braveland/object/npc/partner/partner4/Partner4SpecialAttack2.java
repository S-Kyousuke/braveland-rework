package th.skyousuke.braveland.object.npc.partner.partner4;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner4SpecialAttack2 extends AbstractActionState {

    private static final float DASH_SPEED = 150f;

    public Partner4SpecialAttack2() {
        super(Assets.instance.partner4Atlas, "partner4_satk2", 6, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 37, -45, -5);
        setKeyFrameData(1, 35, 56, -58, -5);
        setKeyFrameData(2, 35, 65, -60, -39);
        setKeyFrameData(3, 35, 65, -52, -5);
        setKeyFrameData(4, 35, 65, -65, -5);
        setKeyFrameData(5, 35, 65, -55, -10);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner4) {
        return 0.3f;
    }

    @Override
    protected void initHotBox(AbstractCharacter partner4) {
        HotBox hotBox1 = new HotBox(-52, 10, 125, 100, new Damage(100f, 150f, ViewDirection.RIGHT));
        hotBox1.setMaxTarget(3);
        hotBox1.getTargets().addAll(partner4.getEnemies());

        HotBox hotBox2 = new HotBox(0, -35, 133, 127, new Damage(100f, 200f, ViewDirection.RIGHT));
        hotBox2.setMaxTarget(3);
        hotBox2.getTargets().addAll(partner4.getEnemies());

        HotBox hotBox5 = new HotBox(0, -5, 120, 95, new Damage(300f, 350f, ViewDirection.RIGHT));
        hotBox5.setMaxTarget(3);
        hotBox5.getTargets().addAll(partner4.getEnemies());

        addHotBox(1, hotBox1);
        addHotBox(2, hotBox2);
        addHotBox(5, hotBox5);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner4) {
        partner4.setAttacking(true);
        partner4.getVelocity().setZero();
    }

    @Override
    protected void onActionExit(AbstractCharacter partner4) {
        partner4.setAttacking(false);
        partner4.getVelocity().setZero();
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner4) {
        partner4.getActionStateMachine().changeState(new Partner4Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter partner4, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            partner4.getVelocity().x = (partner4.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * DASH_SPEED;
        }
    }
}
