package th.skyousuke.braveland.object.npc.partner.partner2;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner2Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public Partner2Attack() {
        super(Assets.instance.partner2Atlas, "partner2_atk", 7, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 59, -44, 0);
        setKeyFrameData(1, 35, 59, -46, 0);
        setKeyFrameData(2, 35, 58, -46, 0);
        setKeyFrameData(3, 35, 59, -46, 0);
        setKeyFrameData(4, 35, 59, -38, 0);
        setKeyFrameData(5, 35, 62, -39, 0);
        setKeyFrameData(6, 35, 62, -36, 0);
    }

    @Override
    protected void initHotBox(AbstractCharacter partner2) {
        HotBox hotBoxFrame2 = new HotBox(20, 10, 106, 18, new Damage(100f, 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(partner2.getEnemies());

        HotBox hotBoxFrame4 = new HotBox(15, 9, 106, 20, new Damage(80f, 120f, ViewDirection.RIGHT));
        hotBoxFrame4.getTargets().addAll(partner2.getEnemies());

        HotBox hotBoxFrame6 = new HotBox(20, 15, 100, 22, new Damage(120f, 250f, ViewDirection.RIGHT));
        hotBoxFrame6.getTargets().addAll(partner2.getEnemies());

        addHotBox(2, hotBoxFrame2);
        addHotBox(4, hotBoxFrame4);
        addHotBox(6, hotBoxFrame6);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner2) {
        return BASE_FRAME_DURATION / partner2.getAttackSpeed();
    }

    @Override
    protected void onActionEnter(AbstractCharacter partner2) {
        partner2.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter partner2) {
        partner2.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner2) {
        partner2.getActionStateMachine().changeState(new Partner2Stand());
    }
}
