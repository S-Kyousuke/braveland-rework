package th.skyousuke.braveland.npc.monster;

import com.badlogic.gdx.ai.msg.Telegraph;
import th.skyousuke.braveland.AbstractActionState;
import th.skyousuke.braveland.AbstractNpc;

public abstract class Monster extends AbstractNpc implements Telegraph {

    public Monster(float width, float height, AbstractActionState initialActionState) {
        super(width, height, initialActionState, MonsterAiState.ALERT);
    }

    public abstract void attack();
}
