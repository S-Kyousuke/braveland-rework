package th.skyousuke.braveland.object.npc.partner.partner2;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner2SpecialAttack extends AbstractActionState {

    private static final float DASH_SPEED = 200f;

    public Partner2SpecialAttack() {
        super(Assets.instance.partner2Atlas, "partner2_satk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 57, -65, 0);
        setKeyFrameData(1, 35, 56, -44, 0);
        setKeyFrameData(2, 35, 65, -50, -19);
    }

    @Override
    protected void initHotBox(AbstractCharacter partner2) {
        HotBox hotBoxFrame1 = new HotBox(-40, 25, 95, 70, new Damage(50f, 200f, ViewDirection.RIGHT));
        hotBoxFrame1.setHitInterval(0.05f);
        hotBoxFrame1.setMaxHitPerTarget(10);
        hotBoxFrame1.setMaxTarget(99);
        hotBoxFrame1.getTargets().addAll(partner2.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(25, -10, 52, 70, new Damage(300f, 550f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(99);
        hotBoxFrame2.getTargets().addAll(partner2.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner2) {
        return 0.4f;
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner2) {
        partner2.setAttacking(true);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner2) {
        partner2.getActionStateMachine().changeState(new Partner2Stand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter partner2, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            partner2.getVelocity().x = (partner2.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * DASH_SPEED;
        }
    }

    @Override
    protected void onActionExit(AbstractCharacter partner2) {
        partner2.setAttacking(false);
        partner2.getVelocity().x = 0;
    }
}
