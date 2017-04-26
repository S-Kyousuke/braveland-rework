package th.skyousuke.braveland.object.player;

import th.skyousuke.braveland.object.AbstractCharacter;

public class Player extends AbstractCharacter {

    private static final float PLAYER_WIDTH = 35;
    private static final float PLAYER_HEIGHT = 65;

    public Player() {
        super(PLAYER_WIDTH, PLAYER_HEIGHT, new PlayerStand());
    }

    public void attack() {
        getActionStateMachine().changeState(new PlayerAttack());
    }

    public void specialAttack() {
        getActionStateMachine().changeState(new PlayerSpecialAttack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new PlayerWalk());
    }
}
