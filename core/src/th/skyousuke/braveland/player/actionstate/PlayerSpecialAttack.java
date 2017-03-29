package th.skyousuke.braveland.player.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.ViewDirection;


public class PlayerSpecialAttack extends AbstractActionState {

    private static final float JUMP_SPEED = 80f;
    private static final float TAKEOFF_SPEED = 200f;

    private static final float FRAME_DURATION = 0.4f;

    private static final float FLIP_X_OFFSET = 35;

    public PlayerSpecialAttack() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_satk", 1);
        keyFramesData[0].offsetX = -19;
        keyFramesData[0].offsetY = -2;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 66;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_satk", 2);
        keyFramesData[1].offsetX = -24;
        keyFramesData[1].offsetY = -2;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 66;

        keyFramesData[2].region = Assets.instance.playerAtlas.findRegion("player_satk", 3);
        keyFramesData[2].offsetX = -49;
        keyFramesData[2].offsetY = -2;
        keyFramesData[2].hitBoxWidth = 35;
        keyFramesData[2].hitBoxHeight = 66;

        keyFramesData[3].region = Assets.instance.playerAtlas.findRegion("player_satk", 4);
        keyFramesData[3].offsetX = -29;
        keyFramesData[3].offsetY = -15;
        keyFramesData[3].hitBoxWidth = 35;
        keyFramesData[3].hitBoxHeight = 66;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter(AbstractCharacter player) {
        super.enter(player);
        KeyFrameData[] keyFramesData = animation.getKeyFrames();

        HotBox hotBoxFrame2 = new HotBox(15, 3, 12, 25, 100, 1);
        hotBoxFrame2.setKnockBackSpeed(350f);
        hotBoxFrame2.setKnockBackDirection(ViewDirection.RIGHT);
        hotBoxFrame2.getTargets().addAll(player.getEnemies());

        HotBox hotBoxFrame3 = new HotBox(25, -20, 57, 100, 120, 1);
        hotBoxFrame3.setKnockBackSpeed(600f);
        hotBoxFrame3.setKnockBackDirection(ViewDirection.RIGHT);
        hotBoxFrame3.getTargets().addAll(player.getEnemies());

        if (player.getViewDirection() == ViewDirection.RIGHT) {
            keyFramesData[2].hotBoxes.add(hotBoxFrame2);
            keyFramesData[3].hotBoxes.add(hotBoxFrame3);
        } else {
            hotBoxFrame2.flipRightToLeft(FLIP_X_OFFSET);
            hotBoxFrame3.flipRightToLeft(FLIP_X_OFFSET);
            keyFramesData[2].hotBoxes.add(hotBoxFrame2);
            keyFramesData[3].hotBoxes.add(hotBoxFrame3);
        }
    }

    @Override
    public void update(AbstractCharacter player) {
        if (animation.isAnimationFinished(player.getActionStateTime())) {
            player.getActionStateMachine().changeState(new PlayerStand());
        }
        int keyFrameIndex = animation.getKeyFrameIndex(player.getActionStateTime());

        if (keyFrameIndex == 2) {
            player.getVelocity().x = (player.getViewDirection() == ViewDirection.RIGHT ? 1 : -1) * TAKEOFF_SPEED;
            player.getVelocity().y = JUMP_SPEED;
        }
        currentKeyFrame = animation.getKeyFrames()[keyFrameIndex];

        player.getHotBoxes().clear();
        for (HotBox hotBox : currentKeyFrame.hotBoxes) {
            player.getHotBoxes().add(hotBox);
        }
    }

    @Override
    public void exit(AbstractCharacter player) {
        player.getVelocity().x = 0;
    }
}
