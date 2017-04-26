package th.skyousuke.braveland.object.npc;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;


public enum NpcNormalAiState implements State<AbstractNpc> {
    IDLE() {
        @Override
        public void update(AbstractNpc partner) {
            // do nothing ...
        }
    };


    @Override
    public void enter(AbstractNpc partner) {
        // default implementation does nothing
    }

    @Override
    public void exit(AbstractNpc partner) {
        // default implementation does nothing
    }

    @Override
    public boolean onMessage(AbstractNpc partner, Telegram telegram) {
        return false;
    }
}
