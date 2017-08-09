package th.skyousuke.braveland.object.npc;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.MathUtils;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.npc.monster.Monster;
import th.skyousuke.braveland.object.npc.partner.Partner;


public enum NpcFightingAiState implements State<AbstractNpc> {
    WALK_RIGHT() {
        @Override
        public void update(AbstractNpc npc) {
            final boolean hasTarget = updateTarget(npc);
            if (npc.findAbsoluteDistanceToRightWall() < npc.getViewDistance()) {
                npc.getAiStateMachine().changeState(WALK_LEFT);
            } else if (hasTarget && (npc.canSeeTarget() || npc.canHearTarget())) {
                npc.getAiStateMachine().changeState(WALK_INTO_TARGET);
            } else if (npc.isOnGround()) {
                npc.setViewDirection(ViewDirection.RIGHT);
                npc.move(ViewDirection.RIGHT);
            }
        }
    },
    WALK_LEFT() {
        @Override
        public void update(final AbstractNpc npc) {
            final boolean hasTarget = updateTarget(npc);
            if (npc.findAbsoluteDistanceToLeftWall() < npc.getViewDistance()) {
                npc.getAiStateMachine().changeState(WALK_RIGHT);
            } else if (hasTarget && (npc.canSeeTarget() || npc.canHearTarget())) {
                npc.getAiStateMachine().changeState(WALK_INTO_TARGET);
            } else if (npc.isOnGround()) {
                npc.setViewDirection(ViewDirection.LEFT);
                npc.move(ViewDirection.LEFT);
            }
        }
    },
    WALK_INTO_TARGET() {
        @Override
        public void update(final AbstractNpc npc) {
            if (!updateTarget(npc) || (!npc.canSeeTarget() && !npc.canHearTarget())) {
                returnToWalkState(npc);
            } else {
                npc.lookAtTarget();
                if (npc.isTargetInAttackRange()) {
                    npc.getAiStateMachine().changeState(ATTACK);
                } else {
                    npc.walkToTarget();
                }
            }
        }
    },
    ATTACK() {
        @Override
        public void update(final AbstractNpc npc) {
            if (!updateTarget(npc)) {
                returnToWalkState(npc);
            } else if (!npc.isAttacking()) {
                if (!npc.isTargetInAttackRange() || !npc.isLookingAtTarget()) {
                    npc.getAiStateMachine().changeState(WALK_INTO_TARGET);
                } else {
                    int randomNumber = MathUtils.random(0, 3);
                    if (randomNumber != 3) {
                        npc.attack(() -> {
                            if (npc instanceof Partner) {
                                ((Partner) npc).attack();
                            }
                            if (npc instanceof Monster) {
                                ((Monster) npc).attack();
                            }
                        });
                    } else {
                        npc.attack(() -> {
                            if (npc instanceof Partner) {
                                ((Partner) npc).specialAttack();
                            }
                            if (npc instanceof Monster) {
                                ((Monster) npc).attack();
                            }
                        });
                    }
                }
            }
        }
    };

    private static boolean updateTarget(AbstractNpc npc) {
        npc.setTarget(npc.getNearestEnemy());
        return npc.getTarget() != null;
    }

    private static void returnToWalkState(AbstractNpc npc) {
        if (npc.getViewDirection() == ViewDirection.RIGHT) {
            npc.getAiStateMachine().changeState(WALK_RIGHT);
        } else {
            npc.getAiStateMachine().changeState(WALK_LEFT);
        }
    }

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
