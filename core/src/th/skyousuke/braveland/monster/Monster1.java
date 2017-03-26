package th.skyousuke.braveland.monster;


import th.skyousuke.braveland.monster.state.Monster1Attack;
import th.skyousuke.braveland.monster.state.Monster1Stand;

public class Monster1 extends Monster {

    private static final float MONSTER1_WIDTH = 80;
    private static final float MONSTER1_HEIGHT = 80;

    public Monster1() {
        super(MONSTER1_WIDTH, MONSTER1_HEIGHT);

        setState(new Monster1Stand(this));
    }

    @Override
    public void attack() {
        setNextState(new Monster1Attack(this));
    }

    @Override
    public void specialAttack() {
    }


}
