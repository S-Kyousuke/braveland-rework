package th.skyousuke.braveland.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.AbstractCharacter;
import th.skyousuke.braveland.CameraHelper;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.LevelChangeListener;
import th.skyousuke.braveland.PlayerChangeListener;

public abstract class AbstractLevel {

    protected Array<AbstractCharacter> characters = new Array<AbstractCharacter>();
    //protected Array<AbstractGameObject> objects = new Array<AbstractGameObject>();

    private static final int WALL_WIDTH = 200;

    protected Texture mapBackground;
    protected Rectangle floor;
    protected Rectangle leftWall;
    protected Rectangle rightWall;

    protected float width;
    protected float height;

    private PlayerChangeListener playerListener;
    private LevelChangeListener levelListener;

    protected Array<HotBox> hotBoxes = new Array<HotBox>();

    public void update(float delaTime) {
        // general system
        for (AbstractCharacter character : characters) {
            character.update(delaTime);
        }
        // hotbox collision check and response system
        for (AbstractCharacter character : characters) {
            for (HotBox hotBox : hotBoxes) {
                if (hotBox.canAffectOn(character)) {
                    hotBox.damageTo(character, 0);
                }
            }
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
        for (AbstractCharacter character : characters) {
            character.draw(batch);
        }
    }

    public void drawUi(SpriteBatch batch, ShapeRenderer shapeRenderer) {
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);

        for (AbstractCharacter character : characters) {
            character.debug(shapeRenderer);
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
