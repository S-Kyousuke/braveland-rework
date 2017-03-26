package th.skyousuke.braveland.npc.partner.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;

public class Partner1Attack extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    private static final float FRAME_DURATION = 0.3f;

    public Partner1Attack() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[7];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 1);
        keyFramesData[0].offsetX = -2;
        keyFramesData[0].offsetY = -5;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 59;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 2);
        keyFramesData[1].offsetX = -3;
        keyFramesData[1].offsetY = -4;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 59;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 3);
        keyFramesData[2].offsetX = -8;
        keyFramesData[2].offsetY = -7;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 58;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 4);
        keyFramesData[3].offsetX = -12;
        keyFramesData[3].offsetY = -2;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 59;

        keyFramesData[4].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 5);
        keyFramesData[4].offsetX = -11;
        keyFramesData[4].offsetY = -5;
        keyFramesData[4].hitBoxWidth = 35;
        keyFramesData[4].hitBoxHeight = 59;

        keyFramesData[5].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 6);
        keyFramesData[5].offsetX = -8;
        keyFramesData[5].offsetY = 0;
        keyFramesData[5].hitBoxWidth = 35;
        keyFramesData[5].hitBoxHeight = 62;

        keyFramesData[6].region = Assets.instance.partner1Atlas.findRegion("partner1_atk", 7);
        keyFramesData[6].offsetX = -8;
        keyFramesData[6].offsetY = -6;
        keyFramesData[6].hitBoxWidth = 35;
        keyFramesData[6].hitBoxHeight = 62;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter(AbstractCharacter partner1) {
        super.enter(partner1);
        partner1.setAttacking(true);
    }

    @Override
    public void update(AbstractCharacter partner1) {
        if (animation.isAnimationFinished(partner1.getActionStateTime())) {
            partner1.getActionStateMachine().changeState(new Partner1Stand());
            partner1.setAttacking(false);
        }
        currentKeyFrame = animation.getKeyFrame(partner1.getActionStateTime());
    }
}
