package th.skyousuke.braveland.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.player.Player;
import th.skyousuke.braveland.ViewDirection;


public class PlayerSpecialAttack extends PlayerState {

    private static final float JUMP_SPEED = 80f;
    private static final float TAKEOFF_SPEED = 200f;

    private static final float FRAME_DURATION = 0.4f;

    public PlayerSpecialAttack(Player player) {
        this.player = player;

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_satk", 1);
        keyFramesData[0].offsetX = -19;
        keyFramesData[0].offsetY = -2;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 66;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_satk", 2);
        keyFramesData[1].offsetX = -24;
        keyFramesData[1].offsetY = -2;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 66;

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_satk", 3);
        keyFramesData[2].offsetX = -49;
        keyFramesData[2].offsetY = -2;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 66;

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_satk", 4);
        keyFramesData[3].offsetX = -29;
        keyFramesData[3].offsetY = -15;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 66;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
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
        int keyFrameIndex = animation.getKeyFrameIndex(stateTime);

        if (keyFrameIndex == 2) {
            player.getVelocity().x = (player.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            player.getVelocity().y = JUMP_SPEED;
        }
        currentKeyFrame = animation.getKeyFrames()[keyFrameIndex];
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
