package th.skyousuke.braveland.object.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class PlayerJump extends AbstractActionState {

    public PlayerJump() {
        super(Assets.instance.playerAtlas, "player_jump", 2, Animation.PlayMode.LOOP, 35);

        setKeyFrameData(0, 35, 70, -25, -1);
        setKeyFrameData(1, 35, 70, -20, 0);
    }

    @Override
    protected float getFrameDuration(AbstractCharacter player) {
        return 1f;
    }


    @Override
    protected void onActionUpdate(AbstractCharacter player) {
        if (player.isOnGround()) {
            if (!CompareUtils.floatEquals(player.getVelocity().x, 0)) {
                player.getActionStateMachine().changeState(new PlayerWalk());
            } else {
                player.getActionStateMachine().changeState(new PlayerStand());
            }
        }
    }
}
