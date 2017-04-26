package th.skyousuke.braveland.object.npc.monster.monster9;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster9 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster9() {
        super(WIDTH, HEIGHT, new Monster9Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster9Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster9Walk());
    }
}
