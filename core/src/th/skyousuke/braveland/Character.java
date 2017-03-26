package th.skyousuke.braveland;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class Character extends AbstractGameObject {


    protected ViewDirection viewDirection = ViewDirection.RIGHT;

    protected float oldPositionY;
    protected boolean onGround;
    protected float moveSpeed;

    protected Rectangle bound = null;
    protected Array<Rectangle> collisionBoxes = new Array<Rectangle>();

    protected float stateTime;

    public Character(float width, float height) {
        super(width, height);
    }

    @Override
    public void collisionResponseX() {
        for (Rectangle collisionBox : collisionBoxes) {
            updateBound();

            if (collisionBox.overlaps(bound)) {
                if (getVelocity().x > 0) {
                    getPosition().x = collisionBox.x - bound.width;
                } else {
                    getPosition().x = collisionBox.x + collisionBox.width;
                }
                getVelocity().x = 0;
                updateBound();
            }
        }
    }

    @Override
    public void collisionResponseY() {
        for (Rectangle collisionBox : collisionBoxes) {
            updateBound();

            if (collisionBox.overlaps(bound)) {
                if (getVelocity().y > 0) {
                    getPosition().y = collisionBox.y - bound.height;
                } else {
                    getPosition().y = collisionBox.y + collisionBox.getHeight();
                }
                getVelocity().y = 0;
                updateBound();
            }
        }
    }

    public void move(ViewDirection direction) {
        viewDirection = direction;
        if (direction == ViewDirection.RIGHT) {
            getVelocity().x = moveSpeed;
        } else {
            getVelocity().x = -moveSpeed;
        }
    }

    protected abstract void updateBound();

    public ViewDirection getViewDirection() {
        return viewDirection;
    }

    public void setViewDirection(ViewDirection viewDirection) {
        this.viewDirection = viewDirection;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bound.x, bound.y, bound.width, bound.height);
    }

    public void addCollisionBox(Rectangle collisionBox) {
        collisionBoxes.add(collisionBox);
    }

    public boolean isOnGround() {
        return onGround;
    }

    public Rectangle getBound() {
        return bound;
    }
}
