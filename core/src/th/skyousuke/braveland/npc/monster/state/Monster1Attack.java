package th.skyousuke.braveland.npc.monster.state;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.KeyFrameData;

public class Monster1Attack extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 61;

    private static final float FRAME_DURATION = 0.3f;

    public Monster1Attack() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[5];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.monster1Atlas.findRegion("monster1_atk", 1);
        keyFramesData[0].offsetX = -10;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].hitBoxWidth = 62;
        keyFramesData[0].hitBoxHeight = 76;

        keyFramesData[1].region = Assets.instance.monster1Atlas.findRegion("monster1_atk", 2);
        keyFramesData[1].offsetX = -10;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].hitBoxWidth = 62;
        keyFramesData[1].hitBoxHeight = 76;

        keyFramesData[2].region = Assets.instance.monster1Atlas.findRegion("monster1_atk", 3);
        keyFramesData[2].offsetX = -10;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].hitBoxWidth = 62;
        keyFramesData[2].hitBoxHeight = 76;

        keyFramesData[3].region = Assets.instance.monster1Atlas.findRegion("monster1_atk", 4);
        keyFramesData[3].offsetX = -10;
        keyFramesData[3].offsetY = 0;
        keyFramesData[3].hitBoxWidth = 62;
        keyFramesData[3].hitBoxHeight = 76;

        keyFramesData[4].region = Assets.instance.monster1Atlas.findRegion("monster1_atk", 5);
        keyFramesData[4].offsetX = -10;
        keyFramesData[4].offsetY = 0;
        keyFramesData[4].hitBoxWidth = 62;
        keyFramesData[4].hitBoxHeight = 76;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void enter(AbstractCharacter monster1) {
        super.enter(monster1);
        monster1.setAttacking(true);
    }

    @Override
    public void update(AbstractCharacter monster1) {
        if (animation.isAnimationFinished(monster1.getActionStateTime())) {
            monster1.getActionStateMachine().changeState(new Monster1Stand());
            monster1.setAttacking(false);
        }
        currentKeyFrame = animation.getKeyFrame(monster1.getActionStateTime());
    }

}
