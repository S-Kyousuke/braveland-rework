package th.skyousuke.braveland.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.player.Player;

public abstract class PlayerState {

    protected Animation<KeyFrameData> animation;
    protected KeyFrameData currentKeyFrame;
    protected Player player;

    public abstract void handleInput();
    public abstract void update(float stateTime);
    public abstract void draw(SpriteBatch batch, Vector2 position);

    public float getBoundWidth() {
        return currentKeyFrame.boundWidth;
    }
    public  float getBoundHeight() {
        return currentKeyFrame.boundHeight;
    }

}
