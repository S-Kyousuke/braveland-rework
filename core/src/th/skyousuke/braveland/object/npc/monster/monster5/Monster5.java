package th.skyousuke.braveland.object.npc.monster.monster5;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster5 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster5() {
        super(WIDTH, HEIGHT, new Monster5Stand());
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster5Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster5Walk());
    }
}
