package th.skyousuke.braveland.npc.monster.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.KeyFrameData;

public abstract class MonsterAnimationState {

    protected Animation<KeyFrameData> animation;
    protected KeyFrameData currentKeyFrame;

    public abstract void update(float stateTime);
    public abstract void draw(SpriteBatch batch, Vector2 position);

    public float getBoundWidth() {
        return currentKeyFrame.hitBoxWidth;
    }
    public  float getBoundHeight() {
        return currentKeyFrame.hitBoxHeight;
    }
}
