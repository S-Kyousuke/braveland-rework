package th.skyousuke.braveland.object.npc.partner.partner4;


import th.skyousuke.braveland.object.npc.partner.Partner;

public class Partner4 extends Partner {

    private static final float WIDTH = 35;
    private static final float HEIGHT = 65;

    public Partner4() {
        super(WIDTH, HEIGHT, new Partner4Stand());
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Partner4Attack());
    }

    @Override
    public void specialAttack() {
        getActionStateMachine().changeState(new Partner4SpecialAttack());
    }

    public void specialAttack2() {
        getActionStateMachine().changeState(new Partner4SpecialAttack2());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Partner4Walk());
    }


}
