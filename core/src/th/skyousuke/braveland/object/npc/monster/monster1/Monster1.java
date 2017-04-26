package th.skyousuke.braveland.object.npc.monster.monster1;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster1 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster1() {
        super(WIDTH, HEIGHT, new Monster1Stand());

        //TODO
        setAttackRange(60);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster1Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster1Walk());
    }
}
