package th.skyousuke.braveland.npc.monster.state;


import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;

public class Monster1Stand extends AbstractActionState {

    private static final float FLIP_X_OFFSET = 61;

    private static final float FRAME_DURATION = 0.3f;

    public Monster1Stand() {
        super(FLIP_X_OFFSET);

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 1);
        keyFramesData[0].offsetX = -10;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].hitBoxWidth = 62;
        keyFramesData[0].hitBoxHeight = 76;

        keyFramesData[1].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 2);
        keyFramesData[1].offsetX = -10;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].hitBoxWidth = 62;
        keyFramesData[1].hitBoxHeight = 76;

        keyFramesData[2].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 3);
        keyFramesData[2].offsetX = -10;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].hitBoxWidth = 62;
        keyFramesData[2].hitBoxHeight = 76;

        keyFramesData[3].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 4);
        keyFramesData[3].offsetX = -10;
        keyFramesData[3].offsetY = 0;
        keyFramesData[3].hitBoxWidth = 62;
        keyFramesData[3].hitBoxHeight = 76;

        animation = new Animation<KeyFrameData>(FRAME_DURATION, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(AbstractCharacter monster1) {
        currentKeyFrame = animation.getKeyFrame(monster1.getActionStateTime());
        if (!CompareUtils.equals(monster1.getVelocity().x, 0)) {
            monster1.getActionStateMachine().changeState(new Monster1Walk());
        }
    }

}
