package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner5Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner5Attack() {
        super(Assets.instance.partner5Atlas, "partner5_atk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 64, -18, -24);
        setKeyFrameData(1, 35, 64, -8, -17);
        setKeyFrameData(2, 35, 64, -8, -35);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner5) {
        return BASE_FRAME_DURATION / partner5.getAttackSpeed();
    }

    @Override
    protected void initHotBox(AbstractCharacter partner5) {
        HotBox hotBoxFrame2 = new HotBox(37, -12, 84, 85, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(3);
        hotBoxFrame2.getTargets().addAll(partner5.getEnemies());

        addHotBox(2, hotBoxFrame2);
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
}
