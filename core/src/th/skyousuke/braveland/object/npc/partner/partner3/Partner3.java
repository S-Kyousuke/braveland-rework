package th.skyousuke.braveland.object.npc.partner.partner3;


import th.skyousuke.braveland.object.npc.partner.Partner;

public class Partner3 extends Partner {

    private static final float WIDTH = 35;
    private static final float HEIGHT = 65;

    public Partner3() {
        super(WIDTH, HEIGHT, new Partner3Stand());
        setAttackRange(480);
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Partner3Attack());
    }

    @Override
    public void specialAttack() {
        getActionStateMachine().changeState(new Partner3SpecialAttack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Partner3Walk());
    }


}
