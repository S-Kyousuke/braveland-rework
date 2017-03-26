package th.skyousuke.braveland.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.player.Player;
import th.skyousuke.braveland.ViewDirection;

/**
 * Created by Bill on 25/3/2560.
 */
public class PlayerStand extends PlayerState {

    private float frameDuration = 0.3f;

    public PlayerStand(Player player) {
        this.player = player;

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_stand", 1);
        keyFramesData[0].offsetX = -20;
        keyFramesData[0].offsetY = -6;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 66;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_stand", 2);
        keyFramesData[1].offsetX = -25;
        keyFramesData[1].offsetY = -6;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 66;

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_stand", 3);
        keyFramesData[2].offsetX = -26;
        keyFramesData[2].offsetY = -6;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 66;

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_stand", 4);
        keyFramesData[3].offsetX = -25;
        keyFramesData[3].offsetY = -6;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 66;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(ViewDirection.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(ViewDirection.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            player.jump();
        } else if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            player.attack();
        } else if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            player.specialAttack();
        }
    }

    @Override
    public void update(float stateTime) {
        currentKeyFrame = animation.getKeyFrame(stateTime);
        if (!player.isOnGround()) {
            player.setNextState(new PlayerJump(player));
        } else if (!CompareUtils.equals(player.getVelocity().x, 0)) {
            player.setNextState(new PlayerWalk(player));
        }
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 position) {
        if (player.getViewDirection() == ViewDirection.RIGHT) {
            batch.draw(currentKeyFrame.region,
                    position.x + currentKeyFrame.offsetX,
                    position.y + currentKeyFrame.offsetY);
        } else {
            batch.draw(currentKeyFrame.region,
                    position.x - currentKeyFrame.offsetX + 35,
                    position.y + currentKeyFrame.offsetY,
                    -currentKeyFrame.region.getRegionWidth(),
                    currentKeyFrame.region.getRegionHeight());
        }

    }
}
