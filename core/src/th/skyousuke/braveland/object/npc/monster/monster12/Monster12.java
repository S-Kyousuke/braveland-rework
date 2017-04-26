package th.skyousuke.braveland.object.npc.monster.monster12;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster12 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster12() {
        super(WIDTH, HEIGHT, new Monster12Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster12Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster12Walk());
    }
}
