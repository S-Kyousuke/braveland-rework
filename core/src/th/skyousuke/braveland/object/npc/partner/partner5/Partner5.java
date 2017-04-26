package th.skyousuke.braveland.object.npc.partner.partner5;


import th.skyousuke.braveland.object.npc.partner.Partner;

public class Partner5 extends Partner {

    private static final float WIDTH = 35;
    private static final float HEIGHT = 65;

    public Partner5() {
        super(WIDTH, HEIGHT, new Partner5Stand());
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Partner5Attack());
    }

    @Override
    public void specialAttack() {
        getActionStateMachine().changeState(new Partner5SpecialAttack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Partner5Walk());
    }

}
