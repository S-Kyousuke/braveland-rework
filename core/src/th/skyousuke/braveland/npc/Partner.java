package th.skyousuke.braveland.npc;

import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractNpc;
import th.skyousuke.braveland.npc.partner.PartnerAiState;

public abstract class Partner extends AbstractNpc {

    public Partner(float width, float height,  AbstractActionState initialActionState) {
        super(width, height, initialActionState, PartnerAiState.NORMAL);
    }

    public abstract void attack();

    public abstract void specialAttack();

}
