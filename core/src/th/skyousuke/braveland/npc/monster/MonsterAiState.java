package th.skyousuke.braveland.npc.monster;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import th.skyousuke.braveland.AbstractNpc;

public enum MonsterAiState implements State<AbstractNpc> {
    ALERT(){
        @Override
        public void update(AbstractNpc monster) {
            monster.wander();
            if (monster.getTarget() != null
                    && monster.getTarget().getHitBox() != null
                    && monster.getHitBox() != null
                    && (monster.isCanSeeTarget() || monster.isTargetInsideHearDistance())) {
                monster.getAiStateMachine().changeState(ENGAGE);
            }
        }
    },
    ENGAGE(){
        @Override
        public void update(final AbstractNpc monster) {
            if (!monster.isAttacking()
                    && monster.getTarget() != null
                    && monster.getTarget().getHitBox() != null
                    && monster.getHitBox() != null) {
                if (monster.isTargetInsideViewDistance()) {
                    if (monster.isTargetInAttackRange()) {
                        ((Monster) monster).attack();
                    } else {
                        monster.followTarget();
                    }
                    monster.lookAtTarget();
                } else {
                    monster.getAiStateMachine().changeState(ALERT);
                }
            }
        }
    };


    @Override
    public void enter(AbstractNpc entity) {

    }

    @Override
    public void update(AbstractNpc entity) {

    }

    @Override
    public void exit(AbstractNpc entity) {

    }

    @Override
    public boolean onMessage(AbstractNpc entity, Telegram telegram) {
        return false;
    }
}
