package th.skyousuke.braveland.object.npc.monster.monster10;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster10 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster10() {
        super(WIDTH, HEIGHT, new Monster10Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster10Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster10Walk());
    }
}
