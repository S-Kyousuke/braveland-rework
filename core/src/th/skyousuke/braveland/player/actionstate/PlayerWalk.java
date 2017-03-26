package th.skyousuke.braveland.player.actionstate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.AttackBehavior;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.player.Player;

public class PlayerWalk extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    public PlayerWalk() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_walk", 1);
        keyFramesData[0].offsetX = -19;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 66;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_walk", 2);
        keyFramesData[1].offsetX = -25;
        keyFramesData[1].offsetY = -1;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 66;

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_walk", 3);
        keyFramesData[2].offsetX = -27;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 66;

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_walk", 4);
        keyFramesData[3].offsetX = -24;
        keyFramesData[3].offsetY = -3;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 66;

        animation = new Animation<KeyFrameData>(0, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);

    }

    @Override
    public void enter(AbstractCharacter player) {
        super.enter(player);
        final float frameDuration = 200 * 0.3f / player.getMovementSpeed();
        animation.setFrameDuration(frameDuration);
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
            finalPlayer.attack(new AttackBehavior() {
                @Override
                public void attack() {
                    finalPlayer.attack();
                }
            });
        } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            finalPlayer.attack(new AttackBehavior() {
                @Override
                public void attack() {
                    finalPlayer.specialAttack();
                }
            });
        }
    }

    @Override
    public void update(AbstractCharacter player) {
        currentKeyFrame = animation.getKeyFrame(player.getActionStateTime());
        if (!player.isOnGround()) {
            player.getActionStateMachine().changeState(new PlayerJump());
        } else if (CompareUtils.equals(player.getVelocity().x, 0)) {
            player.getActionStateMachine().changeState(new PlayerStand());
        }
    }
}
