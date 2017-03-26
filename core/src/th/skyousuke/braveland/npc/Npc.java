package th.skyousuke.braveland.npc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.AiCharacter;
import th.skyousuke.braveland.CompareUtils;
import th.skyousuke.braveland.npc.state.NpcState;

public abstract class Npc extends AiCharacter {

    private static final float DEFAULT_MOVE_SPEED = 200;

    private NpcState state;
    private NpcState nextState;

    public Npc(float width, float height) {
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

    public void setNextState(NpcState state) {
        nextState = state;
    }

    public NpcState getState() {
        return state;
    }

    public void jump() {
        getVelocity().y = Math.min(moveSpeed * 1.73f, 500f);
    }

    public abstract void attack();

    public abstract void specialAttack();

    public void setState(NpcState state) {
        this.state = state;
    }
}
