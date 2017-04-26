package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner5Heal extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    private static final float HEAL_BOX_WIDTH = 600;
    private static final float FLIP_X_OFFSET = 35;

    public Partner5Heal() {
        super(Assets.instance.partner5Atlas, "partner5_heal", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 67, -9, -2);
        setKeyFrameData(1, 35, 67, -28, -1);
        setKeyFrameData(2, 35, 67, -41, -2);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner5) {
        partner5.setAttacking(true);
        partner5.getVelocity().setZero();
    }

    @Override
    protected void onActionExit(AbstractCharacter partner5) {
        partner5.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner5) {
        partner5.getActionStateMachine().changeState(new Partner5Stand());
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner5) {
        return BASE_FRAME_DURATION / partner5.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter partner5) {
        HotBox hotBoxFrame2 = new HotBox(FLIP_X_OFFSET / 2 - HEAL_BOX_WIDTH / 2, -15, HEAL_BOX_WIDTH, 120, new Damage(-200f));
        hotBoxFrame2.setMaxTarget(2);
        hotBoxFrame2.getTargets().add(partner5);
        hotBoxFrame2.getTargets().addAll(partner5.getAllies());

        addHotBox(2, hotBoxFrame2);
    }
}
