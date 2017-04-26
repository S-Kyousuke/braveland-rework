package th.skyousuke.braveland.object.npc.partner.partner1;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;

public class Partner1Attack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.25f;

    public Partner1Attack() {
        super(Assets.instance.partner1Atlas, "partner1_atk", 7, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 59, -2, -5);
        setKeyFrameData(1, 35, 59, -3, -4);
        setKeyFrameData(2, 35, 58, -8, -7);
        setKeyFrameData(3, 35, 59, -12, -2);
        setKeyFrameData(4, 35, 59, -11, -5);
        setKeyFrameData(5, 35, 62, -8, -0);
        setKeyFrameData(6, 35, 62, -8, -6);
    }

    @Override
    protected void initHotBox(AbstractCharacter partner1) {
        HotBox hotBoxFrame2 = new HotBox(27, 13, 77, 13, new Damage(100f, 150f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(partner1.getEnemies());

        HotBox hotBoxFrame4 = new HotBox(27, 13, 75, 13, new Damage(80f, 120f, ViewDirection.RIGHT));
        hotBoxFrame4.getTargets().addAll(partner1.getEnemies());

        HotBox hotBoxFrame6 = new HotBox(27, 18, 76, 16, new Damage(120f, 250f, ViewDirection.RIGHT));
        hotBoxFrame6.getTargets().addAll(partner1.getEnemies());

        addHotBox(2, hotBoxFrame2);
        addHotBox(4, hotBoxFrame4);
        addHotBox(6, hotBoxFrame6);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter partner1) {
        return BASE_FRAME_DURATION / partner1.getAttackSpeed();
    }


    @Override
    protected void onActionEnter(AbstractCharacter partner1) {
        partner1.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter partner1) {
        partner1.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter partner1) {
        partner1.getActionStateMachine().changeState(new Partner1Stand());
    }
}
