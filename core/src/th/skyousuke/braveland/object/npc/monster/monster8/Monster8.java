package th.skyousuke.braveland.object.npc.monster.monster8;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster8 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster8() {
        super(WIDTH, HEIGHT, new Monster8Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster8Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster8Walk());
    }
}
