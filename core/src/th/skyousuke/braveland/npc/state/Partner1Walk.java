package th.skyousuke.braveland.npc.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.npc.Partner1;

/**
 * Created by Bill on 26/3/2560.
 */
public class Partner1Walk extends NpcState {

    private float frameDuration;
    private Partner1 partner1;

    public Partner1Walk(Partner1 partner1) {
        this.partner1 = partner1;

        frameDuration = 200 * 0.3f / partner1.getMoveSpeed();

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 1);
        keyFramesData[0].offsetX = -8;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].boundWidth = 35;
        keyFramesData[0].boundHeight = 62;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 2);
        keyFramesData[1].offsetX = -8;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].boundWidth = 35;
        keyFramesData[1].boundHeight = 62;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 3);
        keyFramesData[2].offsetX = -8;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].boundWidth = 35;
        keyFramesData[2].boundHeight = 62;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 4);
        keyFramesData[3].offsetX = -8;
        keyFramesData[3].offsetY = -2;
        keyFramesData[3].boundWidth = 35;
        keyFramesData[3].boundHeight = 62;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float stateTime) {
        currentKeyFrame = animation.getKeyFrame(stateTime);
        if (!partner1.isOnGround()) {
            partner1.setNextState(new Partner1Jump(partner1));
        } else if (CompareUtils.equals(partner1.getVelocity().x, 0)) {
            partner1.setNextState(new Partner1Stand(partner1));
        }

        if (partner1.getTarget() != null && partner1.getTarget().getBound() != null && partner1.getBound() != null) {
            partner1.lookAtTarget();
            partner1.followTarget();

            if (partner1.isTargetInAttackRange()) {
                partner1.specialAttack();
            }
        }

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
