package th.skyousuke.braveland;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 acceleration = new Vector2();
    private Vector2 friction = new Vector2();
    private Vector2 origin = new Vector2();

    public AbstractGameObject(float width, float height) {
        origin.x = width / 2;
        origin.y = height / 2;
    }

    public void update(float deltaTime) {
        // update position
        position.x += velocity.x * deltaTime;
        onAfterMoveX();

        position.y += velocity.y * deltaTime;
        onAfterMoveY();

        // update velocity due to acceleration (or gravity)
        velocity.x += acceleration.x * deltaTime;
        velocity.y += acceleration.y * deltaTime;

        // update velocity due to friction
        if (velocity.x > 0) {
            velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
        } else if (velocity.x < 0) {
            velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
        }

        if (velocity.y > 0) {
            velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
        } else if (velocity.y < 0) {
            velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Vector2 getFriction() {
        return friction;
    }

    protected void onAfterMoveX() {
    }

    protected void onAfterMoveY() {
    }

    public abstract void draw(SpriteBatch batch);

    /**
     * Draw the bounding box of the object.
     * @param shapeRenderer shape renderer
     */
    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, origin.x * 2, origin.y *2);
    }

}
