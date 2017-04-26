package th.skyousuke.braveland.object.npc;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import th.skyousuke.braveland.object.AbstractActionState;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.object.AbstractCharacter;

public abstract class AbstractNpc extends AbstractCharacter implements Telegraph {

    private static final int DEFAULT_VIEW_DISTANCE = 200;
    private static final int DEFAULT_HEAR_DISTANCE = 100;
    private static final int DEFAULT_ATTACK_RANGE = 100;

    private static final int FOLLOW_IDLE_DISTANCE = 70;
    private static final int LOOK_AT_IDLE_DISTANCE = 30;

    private StateMachine<AbstractNpc, State<AbstractNpc>> aiStateMachine = new DefaultStateMachine<>(this);

    private float viewDistance = DEFAULT_VIEW_DISTANCE;
    private float hearDistance = DEFAULT_HEAR_DISTANCE;
    private float attackRange = DEFAULT_ATTACK_RANGE;

    public AbstractNpc(float width, float height, AbstractActionState initialActionState,
                       State<AbstractNpc> initialAiState) {
        super(width, height, initialActionState);
        aiStateMachine.setInitialState(initialAiState);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        aiStateMachine.update();
    }

    public ViewDirection getTargetDirection() {
        return getDistanceTo(getTarget()) >= 0 ? ViewDirection.RIGHT : ViewDirection.LEFT;
    }

//    public boolean canAttackTarget() {
//        return isTargetInAttackRange() && !isAttacking();
//    }

    public boolean isTargetInAttackRange() {
        return Math.abs(getDistanceTo(getTarget())) <= attackRange;
    }

    public void followTarget() {
        final float distanceToTarget = getDistanceTo(getTarget());
        if (Math.abs(distanceToTarget) > FOLLOW_IDLE_DISTANCE) {
            move(getTargetDirection());
        }
    }

    public void lookAtTarget() {
        final float distanceToTarget = getDistanceTo(getTarget());
        if (Math.abs(distanceToTarget) > LOOK_AT_IDLE_DISTANCE) {
            setViewDirection(getTargetDirection());
        }
    }

    public void walkAwayFromTarget() {
        move(getTargetDirection().getOpposite());
    }

    public void walkToTarget() {
        move(getTargetDirection());
    }

    public boolean isLookingAtTarget() {
        return getTargetDirection() == getViewDirection();
    }

    public boolean canSeeTarget() {
        return Math.abs(getDistanceTo(getTarget())) <= viewDistance && isLookingAtTarget();
    }

    public boolean canHearTarget() {
        return Math.abs(getDistanceTo(getTarget())) <= hearDistance;
    }

    public StateMachine<AbstractNpc, State<AbstractNpc>> getAiStateMachine() {
        return aiStateMachine;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public float getHearDistance() {
        return hearDistance;
    }

    public void setHearDistance(float hearDistance) {
        this.hearDistance = hearDistance;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }

    public float findAbsoluteDistanceToLeftWall() {
        return getListener().findAbsoluteDistanceToLeftWall(this);
    }

    public float findAbsoluteDistanceToRightWall() {
        return getListener().findAbsoluteDistanceToRightWall(this);
    }
}
