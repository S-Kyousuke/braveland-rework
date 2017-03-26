package th.skyousuke.braveland.monster.state;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.KeyFrameData;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.monster.Monster1;

public class Monster1Stand extends MonsterState{

    private float frameDuration = 0.3f;
    private Monster1 monster1;

    public Monster1Stand(Monster1 monster1) {
        this.monster1 = monster1;

        KeyFrameData[] keyFramesData = new KeyFrameData[4];
        for (int i = 0; i < keyFramesData.length; i++) {
            keyFramesData[i] = new KeyFrameData();
        }
        keyFramesData[0].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 1);
        keyFramesData[0].offsetX = -10;
        keyFramesData[0].offsetY = 0;
        keyFramesData[0].boundWidth = 62;
        keyFramesData[0].boundHeight = 76;

        keyFramesData[1].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 2);
        keyFramesData[1].offsetX = -10;
        keyFramesData[1].offsetY = 0;
        keyFramesData[1].boundWidth = 62;
        keyFramesData[1].boundHeight = 76;

        keyFramesData[2].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 3);
        keyFramesData[2].offsetX = -10;
        keyFramesData[2].offsetY = 0;
        keyFramesData[2].boundWidth = 62;
        keyFramesData[2].boundHeight = 76;

        keyFramesData[3].region = Assets.instance.monster1Atlas.findRegion("monster1_stand", 4);
        keyFramesData[3].offsetX = -10;
        keyFramesData[3].offsetY = 0;
        keyFramesData[3].boundWidth = 62;
        keyFramesData[3].boundHeight = 76;

        animation = new Animation<KeyFrameData>(frameDuration, keyFramesData);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float stateTime) {
        currentKeyFrame = animation.getKeyFrame(stateTime);
        if (!CompareUtils.equals(monster1.getVelocity().x, 0)) {
            monster1.setNextState(new Monster1Walk(monster1));
        }

        if (monster1.getTarget() != null && monster1.getTarget().getBound() != null && monster1.getBound() != null) {
            monster1.lookAtTarget();
            monster1.followTarget();

            if (monster1.isTargetInAttackRange()) {
                monster1.attack();
            }
        }

//        if (!monster1.isOnGround()) {
//            monster1.setNextState(new Partner1Jump((Partner1) npc));
//        } else if (!CompareUtils.equals(monster1.getVelocity().x, 0)) {
//            monster1.setNextState(new Partner1Walk((Partner1) npc));
//        }
//
//        if (partner1.getPlayer() != null) {
//            partner1.lookAtTarget();
//            partner1.followPlayer();
//        }
//        if (partner1.isTargetInAttackRange (partner1.getPlayer().getBound())) {
//            partner1.specialAttack();
//        }

    }

    @Override
    public void draw(SpriteBatch batch, Vector2 position) {
        if (monster1.getViewDirection() == ViewDirection.RIGHT) {
            batch.draw(currentKeyFrame.region,
                    position.x + currentKeyFrame.offsetX,
                    position.y + currentKeyFrame.offsetY);
        } else {
            batch.draw(currentKeyFrame.region,
                    position.x - currentKeyFrame.offsetX + 61,
                    position.y + currentKeyFrame.offsetY,
                    -currentKeyFrame.region.getRegionWidth(),
                    currentKeyFrame.region.getRegionHeight());
        }
    }
}
