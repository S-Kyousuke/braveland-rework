package th.skyousuke.braveland.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.object.AbstractGameObject;

public class CameraHelper {

    private final Vector2 position = new Vector2();

    public static final float MIN_SPEED = 0.01f;
    public static final float MAX_SPEED = 1f;

    private float zoom = 1.0f;
    private float speed = 0.1f;

    private float leftMost;
    private float rightMost;
    private float topMost;
    private float bottomMost;

    private AbstractGameObject target;


    public void setCameraBound(float levelWidth, float levelHeight) {
        leftMost = 0;
        bottomMost = 0;
        rightMost= levelWidth;
        topMost = levelHeight;
    }

    public void setSpeed(float speed) {
        this.speed = MathUtils.clamp(speed, MIN_SPEED, MAX_SPEED);
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public void addPosition(float x, float y) {
        this.position.add(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void addZoom(float amount) {
        setZoom(zoom + amount);
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void applyTo(OrthographicCamera camera) {

        final float halfViewportWidth = camera.viewportWidth * 0.5f;
        final float halfViewportHeight = camera.viewportHeight * 0.5f;
        final float maxHorizontalZoom = (rightMost - leftMost) / (halfViewportWidth * 2);
        final float maxVerticalZoom = (topMost - bottomMost) / (halfViewportHeight * 2);

        final float maxZoom = Math.min(maxHorizontalZoom, maxVerticalZoom);
        final float minZoom = 0.01f;
        zoom = MathUtils.clamp(zoom, minZoom, maxZoom);

        keepInLevel(halfViewportWidth, halfViewportHeight);

        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();
    }

    public AbstractGameObject getTarget() {
        return target;
    }

    public void setTarget(AbstractGameObject target) {
        this.target = target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(AbstractGameObject target) {
        return this.target.equals(target);
    }

    public void update() {
        if (!hasTarget())
            return;


        position.x += (target.getPosition().x + target.getOrigin().x - position.x) * speed;
        position.y += (target.getPosition().y + target.getOrigin().y - position.y) * speed;
    }

    public void setPositionToTarget() {
        if (target != null) {
            position.x = target.getPosition().x + target.getOrigin().x;
            position.y = target.getPosition().y + target.getOrigin().y;
        }
    }

    private void keepInLevel(float halfViewportWidth, float halfViewportHeight) {
        if (position.x > rightMost - halfViewportWidth * zoom) {
            position.x = rightMost - halfViewportWidth * zoom;
        } else if (position.x < leftMost + halfViewportWidth * zoom)
            position.x = leftMost + halfViewportWidth * zoom;

        if (position.y > topMost - halfViewportHeight * zoom)
            position.y = topMost - halfViewportHeight * zoom;
        else if (position.y < bottomMost + halfViewportHeight * zoom)
            position.y = bottomMost + halfViewportHeight * zoom;
    }

}
