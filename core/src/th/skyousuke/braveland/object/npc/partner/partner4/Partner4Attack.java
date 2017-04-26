package th.skyousuke.braveland.object.npc.partner.partner4;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner4Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner4Attack() {
        super(Assets.instance.partner4Atlas, "partner4_atk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 67, -47, -45);
        setKeyFrameData(1, 35, 67, -16, -15);
        setKeyFrameData(2, 35, 64, -16, -41);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner4) {
        return BASE_FRAME_DURATION / partner4.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter partner4) {
        HotBox hotBoxFrame1 = new HotBox(13, 13, 25, 25, new Damage(50f, 80f, ViewDirection.RIGHT));
        hotBoxFrame1.setMaxTarget(99);
        hotBoxFrame1.setMaxHitPerTarget(99);
        hotBoxFrame1.setHitInterval(0.1f);
        hotBoxFrame1.getTargets().addAll(partner4.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(13, -10, 130, 95, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(3);
        hotBoxFrame2.getTargets().addAll(partner4.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner4) {
        partner4.setAttacking(true);
        partner4.getVelocity().setZero();
    }

    @Override
    protected void onActionExit(AbstractCharacter partner4) {
        partner4.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner4) {
        partner4.getActionStateMachine().changeState(new Partner4Stand());
    }
}
