package th.skyousuke.braveland.object.npc.monster;

import com.badlogic.gdx.ai.msg.Telegraph;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.object.npc.AbstractNpc;
import th.skyousuke.braveland.object.npc.NpcNormalAiState;

public abstract class Monster extends AbstractNpc implements Telegraph {

    public Monster(float width, float height, AbstractActionState initialActionState) {
        super(width, height, initialActionState, NpcNormalAiState.IDLE);
    }

    public abstract void attack();
}
