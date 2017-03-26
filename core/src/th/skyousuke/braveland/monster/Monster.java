package th.skyousuke.braveland.monster;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.AiCharacter;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.monster.state.MonsterState;

public abstract class Monster extends AiCharacter {

    private static final float DEFAULT_MOVE_SPEED = 200;

    private MonsterState state;
    private MonsterState nextState;

    public Monster(float width, float height) {
        super(width, height);

        moveSpeed = DEFAULT_MOVE_SPEED;
    }

    @Override
    public void draw(SpriteBatch batch) {
        state.draw(batch, getPosition());
    }

    @Override
    public void update(float deltaTime) {
        if (nextState != null) {
            state = nextState;
            stateTime = 0;
            nextState = null;
        }

        stateTime += deltaTime;
        state.update(stateTime);
        updateBound();
        // AI

        super.update(deltaTime);
        updateBound();
        onGround = CompareUtils.equals(oldPositionY, getPosition().y);
        if (onGround) {
            getFriction().set(FLOOR_FRICTION, AIR_FRICTION);
        } else {
            getFriction().set(AIR_FRICTION, AIR_FRICTION);
        }

        oldPositionY = getPosition().y;
    }

    @Override
    protected void updateBound() {
        if (bound == null) {
            bound = new Rectangle();
        }
        bound.set(getPosition().x,
                getPosition().y,
                state.getBoundWidth(),
                state.getBoundHeight());
    }

    public void setNextState(MonsterState state) {
        nextState = state;
    }

    public MonsterState getState() {
        return state;
    }

    public void jump() {
        getVelocity().y = Math.min(moveSpeed * 1.73f, 500f);
    }

    public abstract void attack();

    public abstract void specialAttack();

    public void setState(MonsterState state) {
        this.state = state;
    }
}
