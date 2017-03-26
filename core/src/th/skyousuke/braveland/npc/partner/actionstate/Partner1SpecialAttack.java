package th.skyousuke.braveland.npc.partner.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.ViewDirection;

public class Partner1SpecialAttack extends AbstractActionState {

    private static final float JUMP_SPEED = 80f;
    private static final float TAKEOFF_SPEED = 200f;

    private static final float FLIP_X_OFFSET = 35;

    private static final float FRAME_DURATION = 0.4f;

    public Partner1SpecialAttack() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 1);
        keyFramesData[0].offsetX = -8;
        keyFramesData[0].offsetY = -4;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 57;

        keyFramesData[1].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 2);
        keyFramesData[1].offsetX = -9;
        keyFramesData[1].offsetY = -5;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 56;

        keyFramesData[2].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 3);
        keyFramesData[2].offsetX = -49;
        keyFramesData[2].offsetY = -3;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 65;

        keyFramesData[3].region = Assets.instance.partner1Atlas.findRegion("partner1_satk", 4);
        keyFramesData[3].offsetX = -26;
        keyFramesData[3].offsetY = -10;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 59;

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

        int keyFrameIndex = animation.getKeyFrameIndex(partner1.getActionStateTime());

        if (keyFrameIndex == 2) {
            partner1.getVelocity().x = (partner1.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            partner1.getVelocity().y = JUMP_SPEED;
        }
        currentKeyFrame = animation.getKeyFrames()[keyFrameIndex];
    }

    @Override
    public void exit(AbstractCharacter partner1) {
        partner1.getVelocity().x = 0;
    }
}
