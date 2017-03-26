package th.skyousuke.braveland.npc.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.npc.Partner1;
import th.skyousuke.braveland.ViewDirection;

public class Partner1SpecialAttack extends NpcState {

    private static final float JUMP_SPEED = 80f;
    private static final float TAKEOFF_SPEED = 200f;

    private float frameDuration = 0.4f;
    private Partner1 partner1;

    public Partner1SpecialAttack(Partner1 partner1) {
        this.partner1 = partner1;

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 1);
        keyFramesData[0].offsetX = -8;
        keyFramesData[0].offsetY = -4;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 57;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 2);
        keyFramesData[1].offsetX = -9;
        keyFramesData[1].offsetY = -5;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 56;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 3);
        keyFramesData[2].offsetX = -49;
        keyFramesData[2].offsetY = -3;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 65;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 4);
        keyFramesData[3].offsetX = -26;
        keyFramesData[3].offsetY = -10;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 59;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void update(float stateTime) {
        if (animation.isAnimationFinished(stateTime)) {
            partner1.setNextState(new Partner1Stand(partner1));
        }

        int keyFrameIndex = animation.getKeyFrameIndex(stateTime);

        if (keyFrameIndex == 2) {
            partner1.getVelocity().x = (partner1.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            partner1.getVelocity().y = JUMP_SPEED;
        }
        currentKeyFrame = animation.getKeyFrames()[keyFrameIndex];
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 position) {
        if (partner1.getViewDirection() == ViewDirection.RIGHT) {
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
