package th.skyousuke.braveland.object.npc.monster.monster11;


import th.skyousuke.braveland.object.npc.monster.Monster;

public class Monster11 extends Monster {

    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    public Monster11() {
        super(WIDTH, HEIGHT, new Monster11Stand());
        //TODO
        setAttackRange(50);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Monster11Attack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Monster11Walk());
    }
}
