package th.skyousuke.braveland.object;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;

public abstract class AbstractActionState implements State<AbstractCharacter> {

    private static final int START_REGION_INDEX = 1;

    protected Animation<KeyFrameData> animation;
    protected KeyFrameData currentKeyFrame;

    private final KeyFrameData[] keyFramesData;

    private final float flipXOffset;
    private final float hitBoxFlipXOffset;

    private int oldKeyFrameIndex;

    public AbstractActionState(TextureAtlas atlas, String region, int frameCount, Animation.PlayMode playMode, float flipXOffset) {
        this(atlas, region, frameCount, playMode, flipXOffset, 0);
    }

    public AbstractActionState(TextureAtlas atlas, String region, int frameCount, Animation.PlayMode playMode, float flipXOffset, float hitBoxFlipXOffset) {
        keyFramesData = new KeyFrameData[frameCount];
        for (int i = 0; i < frameCount; i++) {
            keyFramesData[i] = new KeyFrameData();
            keyFramesData[i].region = atlas.findRegion(region, START_REGION_INDEX + i);
        }
        animation = new Animation<>(0, keyFramesData);
        animation.setPlayMode(playMode);

        this.flipXOffset = flipXOffset;
        this.hitBoxFlipXOffset = hitBoxFlipXOffset;
    }

    @Override
    public final void enter(AbstractCharacter character) {
        animation.setFrameDuration(getFrameDuration(character));
        character.setActionStateTime(0);
        currentKeyFrame = animation.getKeyFrame(0);
        onKeyFrameChange(character, -1, 0);

        initHotBox(character);
        for (KeyFrameData keyFrameData : keyFramesData) {
            for (HotBox hotBox : keyFrameData.hotBoxes) {
                hotBox.setFlipX(character.getViewDirection() == ViewDirection.LEFT, flipXOffset);
            }
        }
        onActionEnter(character);
    }

    @Override
    public final void exit(AbstractCharacter character) {
        onActionExit(character);
    }

    @Override
    public boolean onMessage(AbstractCharacter character, Telegram telegram) {
        return false;
    }

    protected void onActionEnter(AbstractCharacter character) {
    }

    protected void onActionUpdate(AbstractCharacter character) {
    }

    protected void onActionExit(AbstractCharacter character) {
    }

    protected void onActionFinish(AbstractCharacter character) {
    }

    protected void onKeyFrameChange(AbstractCharacter character, int oldKeyFrameIndex, int newKeyFrameIndex) {
    }

    protected void initHotBox(AbstractCharacter character) {
    }

    protected abstract float getFrameDuration(AbstractCharacter character);

    protected final void setKeyFrameData(int index, float hitBoxWidth, float hitBoxHeight, float offsetX, float offsetY) {
        keyFramesData[index].hitBoxWidth = hitBoxWidth;
        keyFramesData[index].hitBoxHeight = hitBoxHeight;
        keyFramesData[index].offsetX = offsetX;
        keyFramesData[index].offsetY = offsetY;
    }

    protected final void addHotBox(int keyFrameIndex, HotBox hotBox) {
        keyFramesData[keyFrameIndex].hotBoxes.add(hotBox);
    }

    @Override
    public final void update(AbstractCharacter character) {
        final int currentKeyFrameIndex = animation.getKeyFrameIndex(character.getActionStateTime());
        if (oldKeyFrameIndex != currentKeyFrameIndex) {
            currentKeyFrame = animation.getKeyFrames()[currentKeyFrameIndex];
            onKeyFrameChange(character, oldKeyFrameIndex, currentKeyFrameIndex);
            oldKeyFrameIndex = currentKeyFrameIndex;
        }
        onActionUpdate(character);
        if (animation.getPlayMode() == Animation.PlayMode.NORMAL && animation.isAnimationFinished(character.getActionStateTime())) {
            onActionFinish(character);
        }
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

    public float getHitBoxFlipXOffset() {
        return hitBoxFlipXOffset;
    }

    public Array<HotBox> getHotBoxes() {
        return currentKeyFrame.hotBoxes;
    }

    private class KeyFrameData {
        private TextureRegion region;
        private float offsetX;
        private float offsetY;
        private float hitBoxWidth;
        private float hitBoxHeight;
        private Array<HotBox> hotBoxes = new Array<>();
    }
}
