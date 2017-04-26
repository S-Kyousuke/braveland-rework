package th.skyousuke.braveland.object.npc.partner.partner5;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner5SpecialAttack extends AbstractActionState {

    public Partner5SpecialAttack() {
        super(Assets.instance.partner5Atlas, "partner5_satk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 66, -8, -1);
        setKeyFrameData(1, 35, 65, -8, -1);
        setKeyFrameData(2, 35, 65, -8, -1);
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner5) {
        partner5.getVelocity().setZero();
        partner5.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter partner5) {
        partner5.getVelocity().setZero();
        partner5.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner5) {
        partner5.getActionStateMachine().changeState(new Partner5Stand());
    }

    @Override
    protected float getFrameDuration(AbstractCharacter character) {
        return 0.2f;
    }

    @Override
    protected void initHotBox(AbstractCharacter partner5) {
        HotBox hotBoxFrame1 = new HotBox(62, 12, 67, 38, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame1.setMaxTarget(3);
        hotBoxFrame1.getTargets().addAll(partner5.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(62, 12, 67, 38, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame2.setMaxTarget(3);
        hotBoxFrame2.getTargets().addAll(partner5.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
    }
}
