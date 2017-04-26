package th.skyousuke.braveland.object.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class PlayerWalk extends AbstractActionState {

    private static final float BASE_FRAME_DURATION = 0.3f;

    public PlayerWalk() {
        super(Assets.instance.playerAtlas, "player_walk", 4, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 66, -19, 0);
        setKeyFrameData(1, 35, 66, -25, -1);
        setKeyFrameData(2, 35, 66, -27, 0);
        setKeyFrameData(3, 35, 66, -24, -3);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter player) {
        return AbstractCharacter.DEFAULT_MOVE_SPEED * BASE_FRAME_DURATION / player.getMovementSpeed();
    }

    @Override
    public void handleInput(AbstractCharacter player) {
        final Player finalPlayer = (Player) player;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            finalPlayer.move(ViewDirection.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            finalPlayer.move(ViewDirection.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            finalPlayer.jump();
        } else if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            finalPlayer.attack(finalPlayer::attack);
        } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            finalPlayer.attack(finalPlayer::specialAttack);
        }
    }

    @Override
    protected void onActionUpdate(AbstractCharacter player) {
        if (!player.isOnGround()) {
            player.getActionStateMachine().changeState(new PlayerJump());
        } else if (CompareUtils.floatEquals(player.getVelocity().x, 0)) {
            player.getActionStateMachine().changeState(new PlayerStand());
        }
    }
}
