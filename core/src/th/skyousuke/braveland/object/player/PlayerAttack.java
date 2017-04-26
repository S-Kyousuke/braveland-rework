package th.skyousuke.braveland.object.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;


public class PlayerAttack extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public PlayerAttack() {
        super(Assets.instance.playerAtlas, "player_atk", 4, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 66, -25, -3);
        setKeyFrameData(1, 35, 66, -20, 0);
        setKeyFrameData(2, 35, 66, -20, 0);
        setKeyFrameData(3, 35, 66, -17, 0);
    }

    @Override
    protected void initHotBox(AbstractCharacter player) {
        HotBox hotBoxFrame1 = new HotBox(25, 15, 75, 11, new Damage(100f, 150f, ViewDirection.RIGHT));
        hotBoxFrame1.getTargets().addAll(player.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(25, 15, 75, 11, new Damage(120f, 200f, ViewDirection.RIGHT));
        hotBoxFrame3.getTargets().addAll(player.getEnemies());

        addHotBox(1, hotBoxFrame1);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected void onActionFinish(AbstractCharacter player) {
        player.getActionStateMachine().changeState(new PlayerStand());
    }

    @Override
    protected float getFrameDuration(AbstractCharacter player) {
        return BASE_FRAME_DURATION / player.getAttackSpeed();
    }

    @Override
    protected void onActionEnter(AbstractCharacter player) {
        player.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter player) {
        player.setAttacking(false);
    }
}
