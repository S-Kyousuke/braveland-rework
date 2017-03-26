package th.skyousuke.braveland.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.player.Player;
import th.skyousuke.braveland.ViewDirection;


public class PlayerAttack extends PlayerState {

    private float frameDuration = 0.3f;

    public PlayerAttack(Player player) {
        this.player = player;

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_atk", 1);
        keyFramesData[0].offsetX = -25;
        keyFramesData[0].offsetY = -3;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 66;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_atk", 2);
        keyFramesData[1].offsetX = -20;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 66;

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_atk", 3);
        keyFramesData[2].offsetX = -20;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 66;

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_atk", 4);
        keyFramesData[3].offsetX = -17;
        keyFramesData[3].offsetY = 0;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 66;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float stateTime) {
        if (animation.isAnimationFinished(stateTime)) {
            player.setNextState(new PlayerStand(player));
        }
        currentKeyFrame = animation.getKeyFrame(stateTime);
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
