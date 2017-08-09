package th.skyousuke.braveland.object;


import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.Damage;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.utils.CompareUtils;

public abstract class AbstractCharacter extends AbstractGameObject implements ObstacleAware {

    private static final float FLOOR_FRICTION = 1000f;
    private static final float AIR_FRICTION = 100f;

    private static final float MAX_JUMP_SPEED = 500f;
    private static final float JUMP_SPEED_COEFFICIENT = 2.6f;

    public static final int DEFAULT_LEVEL = 1;
    public static final int DEFAULT_EXPERIENCE = 1;
    public static final float DEFAULT_HEALTH = 1000;
    public static final float DEFAULT_DAMAGE = 100;
    public static final float DEFAULT_ARMOR = 1;
    public static final float DEFAULT_MOVE_SPEED = 120;
    public static final float DEFAULT_ATTACK_SPEED = 1;
    public static final int DEFAULT_GAUGE = 0;

    private Array<Rectangle> obstacleBoxes = new Array<>();

    private Rectangle hitBox = new Rectangle();

    private float actionStateTime;
    private ViewDirection viewDirection = ViewDirection.RIGHT;

    protected boolean onGround = true;

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

    private boolean alive = true;

    private Array<AbstractCharacter> enemies = new Array<>();
    private Array<AbstractCharacter> allies = new Array<>();
    private AbstractCharacter target;

    private StateMachine<AbstractCharacter, AbstractActionState> actionStateMachine = new DefaultStateMachine<>(this);

    private CharacterListener listener;

    public AbstractCharacter(float width, float height, AbstractActionState initialActionState) {
        super(width, height);
        actionStateMachine.changeState(initialActionState);
    }

    @Override
    public void update(float deltaTime) {
        if (!alive)
            return;

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
        if (knockingBack && CompareUtils.floatEquals(getVelocity().x, 0)) {
            knockingBack = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        actionStateMachine.getCurrentState().draw(batch, this);
    }

    private float getHitBoxOffsetX() {
        return viewDirection == ViewDirection.LEFT ? getActionStateMachine().getCurrentState().getHitBoxFlipXOffset() : 0;
    }

    @Override
    public void onAfterMoveX() {
        updateHitBoxPosition();
        updateHotBoxPosition();
        for (Rectangle obstacleBox : obstacleBoxes) {

            if (obstacleBox.overlaps(hitBox)) {

                final float centerX = hitBox.x + hitBox.width / 2;
                final float distanceToLeftEdge = Math.abs(centerX - obstacleBox.x);
                final float distanceToRightEdge = Math.abs(centerX - (obstacleBox.x + obstacleBox.width));

                if (distanceToLeftEdge < distanceToRightEdge) {
                    getPosition().x = obstacleBox.x - hitBox.width - getHitBoxOffsetX();
                } else {
                    getPosition().x = obstacleBox.x + obstacleBox.width - getHitBoxOffsetX();
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

                final float centerY = hitBox.y + hitBox.height / 2;
                final float distanceToBottomEdge = Math.abs(centerY - obstacleBox.y);
                final float distanceToTopEdge = Math.abs(centerY - (obstacleBox.y + obstacleBox.height));

                if (distanceToBottomEdge < distanceToTopEdge) {
                    getPosition().y = obstacleBox.y - hitBox.height;
                } else {
                    getPosition().y = obstacleBox.y + obstacleBox.height;
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
        if (knockingBack)
            return;
        getVelocity().y = Math.min(movementSpeed * JUMP_SPEED_COEFFICIENT, MAX_JUMP_SPEED);
    }

    public void attack(AttackBehavior attackBehavior) {
        if (knockingBack)
            return;

        attackBehavior.attack();
    }

    private void updateHitBoxPosition() {
        hitBox.x = getPosition().x + getHitBoxOffsetX();
        hitBox.y = getPosition().y;
    }

    private void updateHotBoxPosition() {
        for (HotBox hotBox : actionStateMachine.getCurrentState().getHotBoxes()) {
            hotBox.updateBoxPosition(getPosition().x, getPosition().y);
        }
    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);

        if (target != null) {
            shapeRenderer.setColor(Color.ORANGE);
            shapeRenderer.line(hitBox.x + hitBox.width / 2, hitBox.y + hitBox.height,
                    target.hitBox.x + target.hitBox.width / 2, target.hitBox.y + target.hitBox.height / 2);
        }

        for (HotBox hotBox : actionStateMachine.getCurrentState().getHotBoxes()) {
            hotBox.debug(shapeRenderer);
        }
    }

    public void takeDamage(Damage damage) {
        health = Math.max(0, health - damage.getValue());
        if (CompareUtils.floatEquals(health, 0)) {
            alive = false;
            if (listener != null)
                listener.onCharacterDead(this);
        }

        if (!CompareUtils.floatEquals(damage.getKnockBackSpeed(), 0)) {
            knockingBack = true;
            changeStateDueToTakingDamage();
            if (damage.getKnockBackDirection() == ViewDirection.RIGHT) {
                getVelocity().x = damage.getKnockBackSpeed();
            } else {
                getVelocity().x = -damage.getKnockBackSpeed();
            }
        }

        damage.getHitSound().play();

        if (listener != null)
            listener.onCharacterTakeDamage(this, damage.getValue());
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

    @Override
    public Array<Rectangle> getObstacleBoxes() {
        return obstacleBoxes;
    }

    public Array<HotBox> getHotBoxes() {
        return actionStateMachine.getCurrentState().getHotBoxes();
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

    public void setListener(CharacterListener listener) {
        this.listener = listener;
    }

    public CharacterListener getListener() {
        return listener;
    }

    public Array<AbstractCharacter> getAllies() {
        return allies;
    }

    public AbstractCharacter getTarget() {
        return target;
    }

    public void setTarget(AbstractCharacter target) {
        this.target = target;
    }

    public AbstractCharacter getNearestAlly() {
        AbstractCharacter nearestAlly = null;
        float minAbsoluteDistance = Float.MAX_VALUE;
        for (AbstractCharacter ally : allies) {
            final float absoluteDistance = Math.abs(getDistanceTo(ally));
            if (absoluteDistance < minAbsoluteDistance) {
                minAbsoluteDistance = absoluteDistance;
                nearestAlly = ally;
            }
        }
        return nearestAlly;
    }

    public AbstractCharacter getNearestEnemy() {
        AbstractCharacter nearestEnemy = null;
        float minAbsoluteDistance = Float.MAX_VALUE;
        for (AbstractCharacter enemy : enemies) {
            final float absoluteDistance = Math.abs(getDistanceTo(enemy));
            if (absoluteDistance < minAbsoluteDistance) {
                minAbsoluteDistance = absoluteDistance;
                nearestEnemy = enemy;
            }
        }
        return nearestEnemy;
    }

    public float getDistanceTo(AbstractCharacter character) {
        float targetCenterX = character.getHitBox().x + character.getHitBox().width / 2;
        float centerX = getHitBox().x + getHitBox().width / 2;
        return targetCenterX - centerX;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
