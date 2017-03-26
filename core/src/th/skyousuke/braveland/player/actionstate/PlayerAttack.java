package th.skyousuke.braveland.player.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.KeyFrameData;


public class PlayerAttack extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    public PlayerAttack() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_atk", 1);
        keyFramesData[0].offsetX = -25;
        keyFramesData[0].offsetY = -3;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 66;
        //hotBoxes[0] = new HotBox(10, 10, 40, 8, 100, 1);

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_atk", 2);
        keyFramesData[1].offsetX = -20;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 66;
        keyFramesData[1].hotBoxes.add(new HotBox(55, 18, 45, 8, 100, 1, 0, null));

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_atk", 3);
        keyFramesData[2].offsetX = -20;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 66;
        //hotBoxes[2] = new HotBox(16, 26, 45, 8, 100, 1);

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_atk", 4);
        keyFramesData[3].offsetX = -17;
        keyFramesData[3].offsetY = 0;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 66;
        keyFramesData[3].hotBoxes.add(new HotBox(55, 18, 45, 8, 100, 1, 0, null));

        animation = new Animation<KeyFrameData>(0, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter(AbstractCharacter player) {
        super.enter(player);
        final float frameDuration = 0.3f / player.getAttackSpeed();
        animation.setFrameDuration(frameDuration);
    }

    @Override
    public void update(AbstractCharacter player) {
        if (animation.isAnimationFinished(player.getActionStateTime())) {
            player.getActionStateMachine().changeState(new PlayerStand());
            return;
        }
        int currentKeyFrameIndex = animation.getKeyFrameIndex(player.getActionStateTime());
        currentKeyFrame = animation.getKeyFrames()[currentKeyFrameIndex];
        player.setHotBoxes(currentKeyFrame.hotBoxes);
    }
}
