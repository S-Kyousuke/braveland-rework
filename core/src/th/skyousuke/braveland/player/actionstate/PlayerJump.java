package th.skyousuke.braveland.player.actionstate;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;

public class PlayerJump extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 35;

    private static final float FRAME_DURATION = 0.3f;

    public PlayerJump() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[2];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.playerAtlas.findRegion("player_jump", 1);
        keyFramesData[0].offsetX = -23;
        keyFramesData[0].offsetY = -1;
        keyFramesData[0].hitBoxWidth = 35;
        keyFramesData[0].hitBoxHeight = 70;

        keyFramesData[1].region = Assets.instance.playerAtlas.findRegion("player_jump", 2);
        keyFramesData[1].offsetX = -18;
        keyFramesData[1].hitBoxWidth = 35;
        keyFramesData[1].hitBoxHeight = 70;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(AbstractCharacter character) {
        currentKeyFrame = animation.getKeyFrame(character.getActionStateTime());
        if (character.isOnGround()) {
            if (!CompareUtils.equals(character.getVelocity().x, 0)) {
                character.getActionStateMachine().changeState(new PlayerWalk());
            } else {
                character.getActionStateMachine().changeState(new PlayerStand());
            }
        }
    }

}
