package th.skyousuke.braveland.object.npc.monster.monster4;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster4 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster4() {
        super(WIDTH, HEIGHT, new Monster4Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster4Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster4Walk());
    }
}
