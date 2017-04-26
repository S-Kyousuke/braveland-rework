package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner5SpecialAttack2 extends AbstractActionState {

    private static final float DASH_SPEED = 150f;

    public Partner5SpecialAttack2() {
        super(Assets.instance.partner5Atlas, "partner5_satk2", 6, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 57, -45, -6);
        setKeyFrameData(1, 35, 56, -50, -31);
        setKeyFrameData(2, 35, 65, -45, -29);
        setKeyFrameData(3, 35, 65, -26, -1);
        setKeyFrameData(4, 35, 65, -36, -2);
        setKeyFrameData(5, 35, 65, -34, -15);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner5) {
        return 0.3f;
    }

    @Override
    protected void initHotBox(AbstractCharacter partner5) {
        HotBox hotBox1 = new HotBox(-33, -25, 115, 120, new Damage(100f, 150f, ViewDirection.RIGHT));
        hotBox1.setMaxTarget(3);
        hotBox1.getTargets().addAll(partner5.getEnemies());

        HotBox hotBox2 = new HotBox(0, -26, 115, 100, new Damage(100f, 200f, ViewDirection.RIGHT));
        hotBox2.setMaxTarget(3);
        hotBox2.getTargets().addAll(partner5.getEnemies());

        HotBox hotBox5 = new HotBox(73, -12, 65, 55, new Damage(300f, 350f, ViewDirection.RIGHT));
        hotBox5.setMaxTarget(3);
        hotBox5.getTargets().addAll(partner5.getEnemies());

        addHotBox(1, hotBox1);
        addHotBox(2, hotBox2);
        addHotBox(5, hotBox5);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner5) {
        partner5.setAttacking(true);
        partner5.getVelocity().setZero();
    }

    @Override
    protected void onActionExit(AbstractCharacter partner5) {
        partner5.setAttacking(false);
        partner5.getVelocity().setZero();
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner5) {
        partner5.getActionStateMachine().changeState(new Partner5Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter partner5, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            partner5.getVelocity().x = (partner5.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * DASH_SPEED;
        }
    }
}
