package th.skyousuke.braveland.object.npc.monster.monster7;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster7 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster7() {
        super(WIDTH, HEIGHT, new Monster7Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster7Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster7Walk());
    }
}
