package th.skyousuke.braveland;


import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractCharacter extends AbstractGameObject {

    private static final float FLOOR_FRICTION = 1000f;
    private static final float AIR_FRICTION = 100f;

    private static final float MAX_JUMP_SPEED = 500f;
    private static final float JUMP_SPEED_COEFFICIENT = 1.73f;

    private static final int DEFAULT_LEVEL = 1;
    private static final int DEFAULT_EXPERIENCE = 1;
    private static final float DEFAULT_HEALTH = 1000;
    private static final float DEFAULT_DAMAGE = 1;
    private static final float DEFAULT_ARMOR = 1;
    private static final float DEFAULT_MOVE_SPEED = 200;
    private static final float DEFAULT_ATTACK_SPEED = 1;
    private static final int DEFAULT_GAUGE = 0;

    private Array<Rectangle> obstacleBoxes = new Array<Rectangle>();

    protected Rectangle hitBox = new Rectangle();
    private Array<HotBox> hotBoxes = new Array<HotBox>();

    protected float actionStateTime;
    protected ViewDirection viewDirection = ViewDirection.RIGHT;

    protected boolean onGround;

    protected int level = DEFAULT_LEVEL;
    protected int experience = DEFAULT_EXPERIENCE;
    protected float health = DEFAULT_HEALTH;
    protected float damage = DEFAULT_DAMAGE;
    protected float armor = DEFAULT_ARMOR;
    protected float movementSpeed = DEFAULT_MOVE_SPEED;
    protected float attackSpeed = DEFAULT_ATTACK_SPEED;
    protected int gauge = DEFAULT_GAUGE;

    private boolean knockingBack;
    private boolean attacking;

    private Array<AbstractCharacter> enemies = new Array<AbstractCharacter>();

    private StateMachine<AbstractCharacter, AbstractActionState> actionStateMachine
            = new DefaultStateMachine<AbstractCharacter, AbstractActionState>(this);

    public AbstractCharacter(float width, float height, AbstractActionState initialActionState) {
        super(width, height);
        actionStateMachine.setInitialState(initialActionState);
    }

    @Override
    public void update(float deltaTime) {
        // animation update
        actionStateMachine.update();
        hitBox.width = actionStateMachine.getCurrentState().getHitBoxWidth();
        hitBox.height = actionStateMachine.getCurrentState().getHitBoxHeight();
        actionStateTime += deltaTime;

        // motion update
        super.update(deltaTime);

        // check on ground ?
        hitBox.y = getPosition().y - 1;
        onGround = false;
        for (Rectangle obstacleBox : obstacleBoxes) {
            if (obstacleBox.overlaps(hitBox)) {
                onGround = true;
                break;
            }
        }
        hitBox.y = getPosition().y;

        // apply friction
        if (onGround) {
            getFriction().set(FLOOR_FRICTION, AIR_FRICTION);
        } else {
            getFriction().set(AIR_FRICTION, AIR_FRICTION);
        }

        // update knock back action action state
        if (knockingBack && CompareUtils.equals(getVelocity().x, 0)) {
            knockingBack = false;
        }


    }

    @Override
    public void draw(SpriteBatch batch) {
        actionStateMachine.getCurrentState().draw(batch, this);
    }

    @Override
    public void onAfterMoveX() {
        updateHitBoxPosition();
        updateHotBoxPosition();
        for (Rectangle obstacleBox : obstacleBoxes) {

            if (obstacleBox.overlaps(hitBox)) {
                if (getVelocity().x > 0) {
                    getPosition().x = obstacleBox.x - hitBox.width;
                } else {
                    getPosition().x = obstacleBox.x + obstacleBox.width;
                }
                updateHitBoxPosition();
                updateHotBoxPosition();
                getVelocity().x = 0;
            }
        }
    }

    @Override
    public void onAfterMoveY() {
        updateHitBoxPosition();
        updateHotBoxPosition();
        for (Rectangle obstacleBox : obstacleBoxes) {

            if (obstacleBox.overlaps(hitBox)) {
                if (getVelocity().y > 0) {
                    getPosition().y = obstacleBox.y - hitBox.height;
                } else {
                    getPosition().y = obstacleBox.y + obstacleBox.getHeight();
                }
                updateHitBoxPosition();
                updateHotBoxPosition();
                getVelocity().y = 0;
            }
        }
    }

    public void move(ViewDirection direction) {
        if (knockingBack)
            return;

        viewDirection = direction;
        if (direction == ViewDirection.RIGHT) {
            getVelocity().x = movementSpeed;
        } else {
            getVelocity().x = -movementSpeed;
        }
    }

    public void jump() {
        getVelocity().y = Math.min(movementSpeed * JUMP_SPEED_COEFFICIENT, MAX_JUMP_SPEED);
    }

    public void attack(AttackBehavior attackBehavior) {
        attackBehavior.attack();
    }

    protected void updateHitBoxPosition() {
        hitBox.x = getPosition().x;
        hitBox.y = getPosition().y;
    }

    protected void updateHotBoxPosition() {
        for (HotBox hotBox : hotBoxes) {
            hotBox.updateBoxPosition(getPosition().x, getPosition().y);
        }
    }

//    protected void setHitBoxSize(float width, float height) {
//        hitBox.width = width;
//        hitBox.height = height;
//    }

//    protected void setHotBoxSize(float width, float height) {
//        hotbox.width = width;
//        hotbox.height = height;
//    }
//
//    protected void setHotBoxRelativePosition(float relativeX, float relativeY) {
//        hotBoxRelativeX = relativeX;
//        hotBoxRelativeY = relativeY;
//    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        if (hitBox != null) {
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        }
        for (HotBox hotBox : hotBoxes) {
            hotBox.debug(shapeRenderer);
        }
    }

    public void takeDamage(float damage, float knockBackSpeed, ViewDirection knockBackDirection) {
        health -= damage;
        if (knockBackDirection == ViewDirection.RIGHT) {
            getVelocity().x = knockBackSpeed;
        } else if (knockBackDirection == ViewDirection.LEFT){
            getVelocity().x = -knockBackSpeed;
        }
        changeStateDueToTakingDamage();
        knockingBack = true;
    }

    protected abstract void changeStateDueToTakingDamage();

    public Rectangle getHitBox() {
        return hitBox;
    }

    public ViewDirection getViewDirection() {
        return viewDirection;
    }

    public void setViewDirection(ViewDirection viewDirection) {
        this.viewDirection = viewDirection;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public Array<Rectangle> getObstacleBoxes() {
        return obstacleBoxes;
    }

    public Array<HotBox> getHotBoxes() {
        return hotBoxes;
    }

    public float getActionStateTime() {
        return actionStateTime;
    }

    public void setActionStateTime(float actionStateTime) {
        this.actionStateTime = actionStateTime;
    }

    public StateMachine<AbstractCharacter, AbstractActionState> getActionStateMachine() {
        return actionStateMachine;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public float getHealth() {
        return health;
    }

    public float getDamage() {
        return damage;
    }

    public float getArmor() {
        return armor;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getGauge() {
        return gauge;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public Array<AbstractCharacter> getEnemies() {
        return enemies;
    }
}
