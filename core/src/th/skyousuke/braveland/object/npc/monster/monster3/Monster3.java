package th.skyousuke.braveland.object.npc.monster.monster3;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster3 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster3() {
        super(WIDTH, HEIGHT, new Monster3Stand());
        //TODO
        setAttackRange(200);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster3Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster3Walk());
    }
}
