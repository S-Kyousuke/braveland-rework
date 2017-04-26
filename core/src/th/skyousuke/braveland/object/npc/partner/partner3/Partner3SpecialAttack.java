package th.skyousuke.braveland.object.npc.partner.partner3;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner3SpecialAttack extends AbstractActionState {

    private static final float ARROW_BASE_SPEED = 200f;
    private static final float ARROW_GRAVITY_MODIFIER = 0f;

    public Partner3SpecialAttack() {
        super(Assets.instance.partner3Atlas, "partner3_satk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 57, -14, -19);
        setKeyFrameData(1, 35, 56, -15, -16);
        setKeyFrameData(2, 35, 65, -15, -16);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner3) {
        return 0.3f;
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner3) {
        partner3.setAttacking(true);
        partner3.getVelocity().setZero();
    }

    @Override
    protected void onActionExit(AbstractCharacter partner3) {
        partner3.setAttacking(false);
        partner3.getVelocity().setZero();
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner3) {
        partner3.getActionStateMachine().changeState(new Partner3Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter partner3, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            final float partnerCenterX = partner3.getHitBox().x + partner3.getHitBox().width / 2;
            final float partnerCenterY = partner3.getHitBox().y + partner3.getHitBox().height / 2;
            final boolean flipX = partner3.getViewDirection() != ViewDirection.RIGHT;

            final float arrowVelocityX = (flipX ? -1 : 1) * ARROW_BASE_SPEED;
            final Arrow arrow = new Arrow(partnerCenterX, partnerCenterY, arrowVelocityX, 0, flipX);
            arrow.getFriction().setZero();
            arrow.setRemainingHit(15);
            arrow.getHotBox().setMaxTarget(99);
            arrow.getHotBox().setMaxHitPerTarget(5);
            arrow.getHotBox().setHitInterval(0.15f);
            arrow.getHotBox().setDamage(new Damage(80, 200, partner3.getViewDirection()));
            arrow.getHotBox().getTargets().addAll(partner3.getEnemies());

            partner3.getListener().onCharacterCreateObject(partner3, arrow, ARROW_GRAVITY_MODIFIER);
        }
    }
}
