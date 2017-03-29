package th.skyousuke.braveland.npc;


import th.skyousuke.braveland.npc.partner.actionstate.Partner1Attack;
import th.skyousuke.braveland.npc.partner.actionstate.Partner1SpecialAttack;
import th.skyousuke.braveland.npc.partner.actionstate.Partner1Stand;
import th.skyousuke.braveland.npc.partner.actionstate.Partner1Walk;

public class Partner1 extends Partner {

    private static final float PARTNER1_WIDTH = 80;
    private static final float PARTNER1_HEIGHT = 80;

    public Partner1() {
        super(PARTNER1_WIDTH, PARTNER1_HEIGHT, new Partner1Stand());
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
