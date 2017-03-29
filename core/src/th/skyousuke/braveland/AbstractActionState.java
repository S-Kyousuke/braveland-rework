package th.skyousuke.braveland;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractActionState implements State<AbstractCharacter> {

    protected Animation<KeyFrameData> animation;
    protected KeyFrameData currentKeyFrame;
    private final float flipXOffset;

    public AbstractActionState(float flipXOffset) {
        this.flipXOffset = flipXOffset;
    }

    @Override
    public void enter(AbstractCharacter character) {
        character.setActionStateTime(0);
        currentKeyFrame = animation.getKeyFrame(0);
    }

    @Override
    public void exit(AbstractCharacter character) {
    }

    @Override
    public boolean onMessage(AbstractCharacter character, Telegram telegram) {
        return false;
    }

    public void handleInput(AbstractCharacter character) {
    }

    public void draw(SpriteBatch batch, AbstractCharacter character) {
        if (character.getViewDirection() == ViewDirection.RIGHT) {
            batch.draw(currentKeyFrame.region,
                    character.getPosition().x + currentKeyFrame.offsetX,
                    character.getPosition().y + currentKeyFrame.offsetY);
        } else {
            batch.draw(currentKeyFrame.region,
                    character.getPosition().x - currentKeyFrame.offsetX + flipXOffset,
                    character.getPosition().y + currentKeyFrame.offsetY,
                    -currentKeyFrame.region.getRegionWidth(),
                    currentKeyFrame.region.getRegionHeight());
        }
    }

    public float getHitBoxWidth() {
        return currentKeyFrame.hitBoxWidth;
    }

    public float getHitBoxHeight() {
        return currentKeyFrame.hitBoxHeight;
    }
}
