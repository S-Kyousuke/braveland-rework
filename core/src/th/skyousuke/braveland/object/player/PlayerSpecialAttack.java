package th.skyousuke.braveland.object.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;


public class PlayerSpecialAttack extends AbstractActionState {

    private static final float JUMP_SPEED = 300f;
    private static final float TAKEOFF_SPEED = 300f;

    public PlayerSpecialAttack() {
        super(Assets.instance.playerAtlas, "player_satk", 4, Animation.PlayMode.NORMAL, 35);

        setKeyFrameData(0, 35, 60, -19, -2);
        setKeyFrameData(1, 35, 58, -24, -2);
        setKeyFrameData(2, 35, 72, -49, -2);
        setKeyFrameData(3, 35, 66, -29, -15);
    }

    @Override
    protected void initHotBox(AbstractCharacter player) {
        HotBox hotBoxFrame2 = new HotBox(15, 3, 12, 25, new Damage(150f, 350f, ViewDirection.RIGHT));
        hotBoxFrame2.getTargets().addAll(player.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(25, -10, 55, 78, new Damage(400f, 500f, ViewDirection.RIGHT));
        hotBoxFrame3.getTargets().addAll(player.getEnemies());

        addHotBox(2, hotBoxFrame2);
        addHotBox(3, hotBoxFrame3);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter player) {
        return 0.4f;
    }

    @Override
    protected void onActionEnter(AbstractCharacter player) {
        player.setAttacking(true);
    }

    @Override
    protected void onActionExit(AbstractCharacter player) {
        player.setAttacking(false);
    }

    @Override
    protected void onActionFinish(AbstractCharacter player) {
        player.getActionStateMachine().changeState(new PlayerStand());
    }

    @Override
    protected void onKeyFrameChange(AbstractCharacter player, int oldKeyFrameIndex, int newKeyFrameIndex) {
        if (newKeyFrameIndex == 2) {
            player.getVelocity().x = (player.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            player.getVelocity().y = JUMP_SPEED;
        }
    }
}
