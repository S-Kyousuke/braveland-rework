package th.skyousuke.braveland.npc.partner.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;


public class Partner1Jump extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    private static final float FRAME_DURATION = 0.3f;

    public Partner1Jump() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[2];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_jump", 1);
        keyFramesData[0].offsetX = -8;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 67;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_jump", 2);
        keyFramesData[1].offsetX = -8;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 67;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(AbstractCharacter partner1) {
        currentKeyFrame = animation.getKeyFrame(partner1.getActionStateTime());
        if (partner1.isOnGround()) {
            if (!CompareUtils.equals(partner1.getVelocity().x, 0)) {
                partner1.getActionStateMachine().changeState(new Partner1Walk());
            } else {
                partner1.getActionStateMachine().changeState(new Partner1Stand());
            }
        }
    }
}
