package th.skyousuke.braveland.object.npc.partner.partner1;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner1SpecialAttack extends AbstractActionState {

    private static final float JUMP_SPEED = 300f;
    private static final float TAKEOFF_SPEED = 300f;

    public Partner1SpecialAttack() {
        super(Assets.instance.partner1Atlas, "partner1_satk", 4, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 57, -8, -4);
        setKeyFrameData(1, 35, 56, -9, -5);
        setKeyFrameData(2, 35, 65, -49, -3);
        setKeyFrameData(3, 35, 59, -26, -10);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner1) {
        return 0.4f;
    }

    @Override
    protected void initHotBox(AbstractCharacter partner1) {
        HotBox hotBoxFrame2 = new HotBox(12, 0, 12, 25, new Damage(400f, 500f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(99);
        hotBoxFrame2.getTargets().addAll(partner1.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(25, -10, 52, 70, new Damage(400f, 500f, ViewDirection.RIGHT));
        hotBoxFrame3.setMaxTarget(99);
        hotBoxFrame3.getTargets().addAll(partner1.getEnemies());

        addHotBox(2, hotBoxFrame2);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner1) {
        partner1.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter partner1) {
        partner1.getVelocity().x = 0;
        partner1.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner1) {
        partner1.getActionStateMachine().changeState(new Partner1Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter partner1, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            partner1.getVelocity().x = (partner1.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            partner1.getVelocity().y = JUMP_SPEED;
        }
    }
}
