package th.skyousuke.braveland.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import th.skyousuke.braveland.object.AbstractGameObject;

public class CameraHelper {

    private static final float MAX_ZOOM_OUT = 1.35f;
    private static final float MAX_ZOOM_IN = 0.2f;

    private final Vector2 position = new Vector2();
    private float zoom = 1.0f;
    private AbstractGameObject target;

    private float levelWidth;
    private float levelHeight;


    public void setLevelDimension(float levelWidth, float levelHeight) {
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
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
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public void applyTo(OrthographicCamera camera) {
        keepInLevel(camera);
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

        final float alpha = 0.1f;
        position.x += (target.getPosition().x + target.getOrigin().x - position.x) * alpha;
        position.y += (target.getPosition().y + target.getOrigin().y - position.y) * alpha;
    }

    public void setPositionToTarget() {
        if (target != null) {
            position.x = target.getPosition().x + target.getOrigin().x;
            position.y = target.getPosition().y + target.getOrigin().y;
        }
    }

    private void keepInLevel(OrthographicCamera camera) {
        final float halfCameraWidth = camera.viewportWidth * 0.5f;
        final float halfCameraHeight = camera.viewportHeight * 0.5f;

        if (position.x > levelWidth - halfCameraWidth * zoom) {
            position.x = levelWidth - halfCameraWidth * zoom;
        } else if (position.x < halfCameraWidth * zoom)
            position.x = halfCameraWidth * zoom;

        if (position.y > levelHeight - halfCameraHeight * zoom)
            position.y = levelHeight - halfCameraHeight * zoom;
        else if (position.y < halfCameraHeight * zoom)
            position.y = halfCameraHeight * zoom;
    }

}
