package th.skyousuke.braveland.player;

import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.player.actionstate.PlayerAttack;
import th.skyousuke.braveland.player.actionstate.PlayerSpecialAttack;
import th.skyousuke.braveland.player.actionstate.PlayerStand;

public class Player extends AbstractCharacter {

    private static final float PLAYER_WIDTH = 80;
    private static final float PLAYER_HEIGHT = 80;

    public Player() {
        super(PLAYER_WIDTH, PLAYER_HEIGHT, new PlayerStand());
    }

    public void attack() {
        getActionStateMachine().changeState(new PlayerAttack());
    }

    public void specialAttack() {
        getActionStateMachine().changeState(new PlayerSpecialAttack());
    }
}
