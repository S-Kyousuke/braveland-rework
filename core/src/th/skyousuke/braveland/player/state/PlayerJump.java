package th.skyousuke.braveland.player.state;

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
public class PlayerJump extends PlayerState {

    private float frameDuration = 0.3f;

    public PlayerJump(Player player) {
        this.player = player;

        KeyFrameData[] keyFramesData = new KeyFrameData[2];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_jump", 1);
        keyFramesData[0].offsetX = -23;
        keyFramesData[0].offsetY = -1;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 70;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_jump", 2);
        keyFramesData[1].offsetX = -18;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 70;


        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float stateTime) {
        currentKeyFrame = animation.getKeyFrame(stateTime);
        if (player.isOnGround()) {
            if (!CompareUtils.equals(player.getVelocity().x, 0)) {
                player.setNextState(new PlayerWalk(player));
            } else {
                player.setNextState(new PlayerStand(player));
            }
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
