package th.skyousuke.braveland.npc.monster;


import th.skyousuke.braveland.npc.monster.state.Monster1Attack;
import th.skyousuke.braveland.npc.monster.state.Monster1Stand;

public class Monster1 extends Monster {

    private static final float MONSTER1_WIDTH = 80;
    private static final float MONSTER1_HEIGHT = 80;

    public Monster1() {
        super(MONSTER1_WIDTH, MONSTER1_HEIGHT, new Monster1Stand());
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster1Attack());
    }


}
