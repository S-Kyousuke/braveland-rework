package th.skyousuke.braveland.object.npc.partner;

import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.npc.AbstractNpc;
import th.skyousuke.braveland.object.npc.NpcNormalAiState;

public abstract class Partner extends AbstractNpc {

    public Partner(float width, float height, AbstractActionState initialActionState) {
        super(width, height, initialActionState, NpcNormalAiState.IDLE);
    }

    public abstract void attack();

    public abstract void specialAttack();


}
