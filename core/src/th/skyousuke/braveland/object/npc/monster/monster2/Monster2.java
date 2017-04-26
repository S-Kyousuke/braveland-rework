package th.skyousuke.braveland.object.npc.monster.monster2;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster2 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster2() {
        super(WIDTH, HEIGHT, new Monster2Stand());
        //TODO
        setAttackRange(60);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster2Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster2Walk());
    }
}
