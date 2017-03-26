package th.skyousuke.braveland.npc.partner;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.MathUtils;
import th.skyousuke.braveland.AbstractNpc;
import th.skyousuke.braveland.AttackBehavior;
import th.skyousuke.braveland.npc.Partner;


public enum PartnerAiState implements State<AbstractNpc> {
    NORMAL() {
        @Override
        public void update(AbstractNpc partner) {

        }
    },
    ALERT() {
        @Override
        public void update(final AbstractNpc partner) {
            if (!partner.isAttacking()
                    && partner.getTarget() != null
                    && partner.getTarget().getHitBox() != null
                    && partner.getHitBox() != null) {

                partner.lookAtTarget();
                partner.followTarget();

                if (partner.isTargetInAttackRange()) {
                    int randomNumber = MathUtils.random(0, 1);
                    if (randomNumber == 0) {
                        partner.attack(new AttackBehavior() {
                            @Override
                            public void attack() {
                                ((Partner) partner).attack();
                            }
                        });
                    } else {
                        partner.attack(new AttackBehavior() {
                            @Override
                            public void attack() {
                                ((Partner) partner).specialAttack();
                            }
                        });
                    }
                }
            }
        }
    };


    @Override
    public void enter(AbstractNpc entity) {
    }

    @Override
    public void exit(AbstractNpc entity) {
    }

    @Override
    public boolean onMessage(AbstractNpc entity, Telegram telegram) {
        return false;
    }
}
