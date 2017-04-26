package th.skyousuke.braveland.object.npc.partner.partner4;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner4SpecialAttack extends AbstractActionState {

    public Partner4SpecialAttack() {
        super(Assets.instance.partner4Atlas, "partner4_satk", 3, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 66, -16, -5);
        setKeyFrameData(1, 35, 65, -16, -5);
        setKeyFrameData(2, 35, 65, -16, -5);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner4) {
        return 0.2f;
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
    protected void initHotBox(AbstractCharacter partner4) {
        HotBox hotBoxFrame1 = new HotBox(55, 20, 88, 48, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame1.getTargets().addAll(partner4.getEnemies());

        HotBox hotBoxFrame2 = new HotBox(55, 20, 88, 48, new Damage(200f, 200f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(partner4.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(2, hotBoxFrame2);
    }
}
