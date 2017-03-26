package th.skyousuke.braveland.npc.partner.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;


public class Partner1Walk extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    public Partner1Walk() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 1);
        keyFramesData[0].offsetX = -8;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 62;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 2);
        keyFramesData[1].offsetX = -8;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 62;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 3);
        keyFramesData[2].offsetX = -8;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 62;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_walk", 4);
        keyFramesData[3].offsetX = -8;
        keyFramesData[3].offsetY = -2;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 62;

        animation = new Animation<KeyFrameData>(0, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void enter(AbstractCharacter partner1) {
        super.enter(partner1);
        final float frameDuration = 200 * 0.3f / partner1.getMovementSpeed();
        animation.setFrameDuration(frameDuration);
    }

    @Override
    public void update(AbstractCharacter partner1) {
        currentKeyFrame = animation.getKeyFrame(partner1.getActionStateTime());
        if (!partner1.isOnGround()) {
            partner1.getActionStateMachine().changeState(new Partner1Jump());
        } else if (CompareUtils.equals(partner1.getVelocity().x, 0)) {
            partner1.getActionStateMachine().changeState(new Partner1Stand());
        }

    }
}
