package th.skyousuke.braveland.object.npc.monster.monster6;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster6 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster6() {
        super(WIDTH, HEIGHT, new Monster6Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster6Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster6Walk());
    }
}
