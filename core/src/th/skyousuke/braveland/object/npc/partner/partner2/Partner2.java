package th.skyousuke.braveland.object.npc.partner.partner2;


import th.skyousuke.braveland.object.npc.partner.Partner;

public class Partner2 extends Partner {

    private static final float WIDTH = 35;
    private static final float HEIGHT = 65;

    public Partner2() {
        super(WIDTH, HEIGHT, new Partner2Stand());

        //getActionStateMachine().changeState(new Partner5Attack());
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Partner2Attack());
    }

    @Override
    public void specialAttack() {
        getActionStateMachine().changeState(new Partner2SpecialAttack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Partner2Walk());
    }
}
