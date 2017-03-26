package th.skyousuke.braveland.npc.partner.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;

/**
 * Created by Bill on 26/3/2560.
 */
public class Partner1Stand extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    private static final float FRAME_DURATION = 0.3f;

    public Partner1Stand() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_stand", 1);
        keyFramesData[0].offsetX = -7;
        keyFramesData[0].offsetY = -2;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 62;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_stand", 2);
        keyFramesData[1].offsetX = -7;
        keyFramesData[1].offsetY = -1;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 62;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_stand", 3);
        keyFramesData[2].offsetX = -7;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 62;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_stand", 4);
        keyFramesData[3].offsetX = -7;
        keyFramesData[3].offsetY = -1;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 62;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }


    @Override
    public void update(AbstractCharacter partner1) {
        currentKeyFrame = animation.getKeyFrame(partner1.getActionStateTime());
        if (!partner1.isOnGround()) {
            partner1.getActionStateMachine().changeState(new Partner1Jump());
        } else if (!CompareUtils.equals(partner1.getVelocity().x, 0)) {
            partner1.getActionStateMachine().changeState(new Partner1Walk());
        }
    }

}
