package th.skyousuke.braveland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractNpc extends AbstractCharacter implements Telegraph {

    private static final int DEFAULT_VIEW_DISTANCE = 250;
    private static final int DEFAULT_HEAR_DISTANCE = 100;


    private StateMachine<AbstractNpc, State<AbstractNpc>> aiStateMachine
            = new DefaultStateMachine<AbstractNpc, State<AbstractNpc>>(this);

    private Array<AbstractCharacter> targets = new Array<AbstractCharacter>();
    private AbstractCharacter target;

    private float viewDistance = DEFAULT_VIEW_DISTANCE;
    private float hearDistance = DEFAULT_HEAR_DISTANCE;

    private float wanderTime;
    private float restTime;
    private ViewDirection wanderDirection;

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

    public boolean isTargetInAttackRange() {
        final int attackRange = 70;
        return Math.abs(getDistanceToTarget()) <= attackRange;
    }

    public void setTarget(AbstractCharacter target) {
        this.target = target;
    }

    public void followTarget() {
        final float distanceThreshold = 70;
        if (onGround && isTargetInsideViewDistance()) {
            if (getDistanceToTarget() > distanceThreshold) {
                move(ViewDirection.RIGHT);
            } else if (getDistanceToTarget() < -distanceThreshold) {
                move(ViewDirection.LEFT);
            }
        }
    }

    public void lookAtTarget() {
        final float distanceThreshold = 30;

        if (getDistanceToTarget() > distanceThreshold) {
            setViewDirection(ViewDirection.RIGHT);
        } else if (getDistanceToTarget() < -distanceThreshold) {
            setViewDirection(ViewDirection.LEFT);
        }
    }

    public AbstractCharacter getTarget() {
        return target;
    }

    /**
     *
     * @return distance to target (signed: target position subtract current position)
     */
    public float getDistanceToTarget() {
        float targetCenterX = target.getHitBox().x + target.getHitBox().width / 2;
        float centerX = hitBox.x + hitBox.width / 2;
        return targetCenterX - centerX;
    }

    public boolean isTargetInsideViewDistance() {
        return Math.abs(getDistanceToTarget()) <= viewDistance;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public void wander() {
        if (onGround) {
            float delta = Gdx.graphics.getDeltaTime();
            if (wanderTime <= 0 && restTime <= 0) {
                int randomNumber = MathUtils.random(0, 1);
                if (randomNumber == 0) {
                    wanderDirection = ViewDirection.LEFT;
                } else {
                    wanderDirection = ViewDirection.RIGHT;
                }
                restTime = MathUtils.random(1.0f, 2.0f);
                float maxTime = 2.0f * 200 / getMovementSpeed();
                float minTime = 0.25f * 200 / getMovementSpeed();
                wanderTime = MathUtils.random(minTime, maxTime);
            } else if (restTime > 0) {
                restTime -= delta;
            } else {
                move(wanderDirection);
                wanderTime -= delta;
            }
        }
    }

    public boolean isLookingAtTarget() {
        if (getDistanceToTarget() > 0) {
            return viewDirection == ViewDirection.RIGHT;
        } else if (getDistanceToTarget() < 0) {
            return viewDirection == ViewDirection.LEFT;
        }
        return true;
    }

    public boolean isCanSeeTarget() {
        return isTargetInsideViewDistance() && isLookingAtTarget();
    }

    public boolean isTargetInsideHearDistance() {
        return Math.abs(getDistanceToTarget()) <= hearDistance;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }

    public StateMachine<AbstractNpc, State<AbstractNpc>> getAiStateMachine() {
        return aiStateMachine;
    }
}
