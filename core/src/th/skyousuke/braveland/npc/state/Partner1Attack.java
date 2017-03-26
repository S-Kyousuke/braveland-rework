package th.skyousuke.braveland.npc.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.npc.Partner1;
import th.skyousuke.braveland.ViewDirection;

public class Partner1Attack extends NpcState {

    private float frameDuration = 0.3f;
    private Partner1 partner1;

    public Partner1Attack(Partner1 partner1) {
        this.partner1 = partner1;

        KeyFrameData[] keyFramesData = new KeyFrameData[7];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 1);
        keyFramesData[0].offsetX = -2;
        keyFramesData[0].offsetY = -5;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 59;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 2);
        keyFramesData[1].offsetX = -3;
        keyFramesData[1].offsetY = -4;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 59;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 3);
        keyFramesData[2].offsetX = -8;
        keyFramesData[2].offsetY = -7;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 58;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 4);
        keyFramesData[3].offsetX = -12;
        keyFramesData[3].offsetY = -2;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 59;

        keyFramesData[4].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 5);
        keyFramesData[4].offsetX = -11;
        keyFramesData[4].offsetY = -5;
        keyFramesData[4].boundWidth = 35;
        keyFramesData[4].boundHeight = 59;

        keyFramesData[5].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 6);
        keyFramesData[5].offsetX = -8;
        keyFramesData[5].offsetY = 0;
        keyFramesData[5].boundWidth = 35;
        keyFramesData[5].boundHeight = 62;

        keyFramesData[6].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 7);
        keyFramesData[6].offsetX = -8;
        keyFramesData[6].offsetY = -6;
        keyFramesData[6].boundWidth = 35;
        keyFramesData[6].boundHeight = 62;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void update(float stateTime) {
        //currentKeyFrame = animation.getKeyFrame(stateTime);
//        if (!npc.isOnGround()) {
//            npc.setNextState(new Partner1Jump((Partner1) npc));
//        } else if (CompareUtils.equals(npc.getVelocity().x, 0)) {
//            npc.setNextState(new Partner1Stand((Partner1) npc));
//        }

        if (animation.isAnimationFinished(stateTime)) {
            partner1.setNextState(new Partner1Stand(partner1));
        }
        currentKeyFrame = animation.getKeyFrame(stateTime);
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
