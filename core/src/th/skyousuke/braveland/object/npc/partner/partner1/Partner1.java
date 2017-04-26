package th.skyousuke.braveland.object.npc.partner.partner1;


import th.skyousuke.braveland.object.npc.partner.Partner;

public class Partner1 extends Partner {

    private static final float WIDTH = 35;
    private static final float HEIGHT = 65;

    public Partner1() {
        super(WIDTH, HEIGHT, new Partner1Stand());
    }

    @Override
    public void attack() {
        getActionStateMachine().changeState(new Partner1Attack());
    }

    @Override
    public void specialAttack() {
        getActionStateMachine().changeState(new Partner1SpecialAttack());
    }

    @Override
    protected void changeStateDueToTakingDamage() {
        getActionStateMachine().changeState(new Partner1Walk());
    }
}
