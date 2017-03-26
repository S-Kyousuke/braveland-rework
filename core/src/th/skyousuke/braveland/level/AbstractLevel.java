package th.skyousuke.braveland.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.AbstractGameObject;
import th.skyousuke.braveland.CameraHelper;
import th.skyousuke.braveland.LevelChangeListener;
import th.skyousuke.braveland.PlayerChangeListener;

public abstract class AbstractLevel {

    protected Array<AbstractGameObject> objects = new Array<AbstractGameObject>();

    private static final int WALL_WIDTH = 200;

    protected Texture mapBackground;
    protected Rectangle floor;
    protected Rectangle leftWall;
    protected Rectangle rightWall;

    protected float width;
    protected float height;

    private PlayerChangeListener playerListener;
    private LevelChangeListener levelListener;

    public void update(float delaTime) {
        for (AbstractGameObject object : objects) {
            object.update(delaTime);
        }
    }

    public void init(CameraHelper cameraHelper) {
    }

    protected void setUpWall() {
        leftWall = new Rectangle(-WALL_WIDTH, 0, WALL_WIDTH, height);
        rightWall = new Rectangle(width, 0, WALL_WIDTH, height);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(mapBackground, 0, 0);
        for (AbstractGameObject object : objects) {
            object.draw(batch);
        }
    }

    public void drawUi(SpriteBatch batch, ShapeRenderer shapeRenderer) {
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);

        for (AbstractGameObject object : objects) {
            object.debug(shapeRenderer);
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setPlayerListener(PlayerChangeListener playerListener) {
        this.playerListener = playerListener;
    }

    public PlayerChangeListener getPlayerListener() {
        return playerListener;
    }

    public LevelChangeListener getLevelListener() {
        return levelListener;
    }

    public void setLevelListener(LevelChangeListener levelListener) {
        this.levelListener = levelListener;
    }

    public void onSpecialKeyPressed() {
    }
}
