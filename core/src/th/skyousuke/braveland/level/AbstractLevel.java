package th.skyousuke.braveland.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.DamageNumber;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.object.*;
import th.skyousuke.braveland.object.npc.partner.Partner;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.object.player.PlayerChangeListener;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;
import th.skyousuke.braveland.utils.FontUtils;

public abstract class AbstractLevel implements CharacterListener {

    private static final int WALL_WIDTH = 200;
    private static final int DEFAULT_GRAVITY_ACCELERATION = -1200;

    private Array<AbstractLevelObject> objects = new Array<>();
    private Array<AbstractCharacter> characters = new Array<>();
    private Array<DamageNumber> damageNumbers = new Array<>();

    private Texture mapBackground;
    private Rectangle floor;
    private Rectangle leftWall;
    private Rectangle rightWall;

    private float width;
    private float height;

    private float gravityAcceleration = DEFAULT_GRAVITY_ACCELERATION;

    private PlayerChangeListener playerListener;
    private LevelListener levelListener;

    public AbstractLevel(Texture mapBackground) {
        this.mapBackground = mapBackground;
        this.width = mapBackground.getWidth();
        this.height = mapBackground.getHeight();

        leftWall = new Rectangle(-WALL_WIDTH, 0, WALL_WIDTH, height);
        rightWall = new Rectangle(width, 0, WALL_WIDTH, height);
        floor = makeFloor();
    }

    protected abstract Rectangle makeFloor();

    public void update(float delaTime) {
        // general system
        for (AbstractLevelObject object : objects) {
            if (object.isAlive()) {
                object.update(delaTime);
            } else {
                objects.removeValue(object, true);
            }
        }
        for (AbstractCharacter character : characters) {
            character.update(delaTime);
        }

        // hotbox system
        for (AbstractLevelObject object : objects) {
            for (HotBox hotBox : object.getHotBoxes()) {
                hotBox.update(delaTime);
            }
        }
        for (AbstractCharacter character : characters) {
            for (HotBox hotBox : character.getHotBoxes()) {
                hotBox.update(delaTime);
            }
        }

        // damage number system
        for (DamageNumber damageNumber : damageNumbers) {
            if (!damageNumber.update(delaTime)) {
                damageNumbers.removeValue(damageNumber, true);
            }
        }
    }

    public void init(CameraHelper cameraHelper) {
        characters.clear();
        objects.clear();
        damageNumbers.clear();

        cameraHelper.setCameraBound(getWidth(), getHeight());
    }

    public void draw(SpriteBatch batch) {
        batch.draw(mapBackground, 0, 0);

        for (AbstractLevelObject object : objects) {
            object.draw(batch);
        }
        for (AbstractCharacter character : characters) {
            character.draw(batch);
        }

        for (DamageNumber damageNumber : damageNumbers) {
            damageNumber.draw(batch);
        }
    }

    public void drawUi(SpriteBatch batch) {
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);

        for (AbstractLevelObject object : objects) {
            object.debug(shapeRenderer);
        }
        for (AbstractCharacter character : characters) {
            character.debug(shapeRenderer);
        }
    }

    public void debugUi(ShapeRenderer shapeRenderer) {
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

    public LevelListener getLevelListener() {
        return levelListener;
    }

    public void setLevelListener(LevelListener levelListener) {
        this.levelListener = levelListener;
    }

    public void onSpecialKeyPressed(int command) {
    }

    @Override
    public void onCharacterTakeDamage(AbstractCharacter character, float damage) {
        final int damageInt = (int) damage;
        final float damageTextWidth = FontUtils.getTextWidth(Assets.instance.font, String.valueOf(damageInt));

        damageNumbers.add(new DamageNumber(damageInt,
                character.getHitBox().x + character.getHitBox().width / 2 - damageTextWidth / 2,
                character.getHitBox().y + character.getHitBox().height));
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration;
    }

    @Override
    public void onCharacterCreateObject(AbstractCharacter character, AbstractLevelObject levelObject, float gravityModifier) {
        objects.add(levelObject);
        applyObstaclesTo(levelObject);
        applyGravityTo(levelObject, gravityModifier);
    }

    private void applyGravityTo(AbstractGameObject gameObject, float modifier) {
        gameObject.getAcceleration().y = gravityAcceleration * modifier;
    }

    private void applyGravityTo(AbstractGameObject gameObject) {
        applyGravityTo(gameObject, 1);
    }

    private void applyObstaclesTo(ObstacleAware obstacleAware) {
        obstacleAware.getObstacleBoxes().add(floor);
        obstacleAware.getObstacleBoxes().add(leftWall);
        obstacleAware.getObstacleBoxes().add(rightWall);
    }

    public void addCharacter(AbstractCharacter character, float positionX, float positionY) {

        //TODO: BAD CODE for testing only >_<
        for (int i = 0; i < characters.size; ++i)  {
            AbstractCharacter existingCharacter = characters.get(i);

            boolean friendlyExistingCharacter = (existingCharacter instanceof Player || existingCharacter instanceof Partner);
            boolean friendlyCharacter = (character instanceof Player || character instanceof Partner);

            if (friendlyCharacter != friendlyExistingCharacter ) {
                character.getEnemies().add(existingCharacter);
                existingCharacter.getEnemies().add(character);
            } else {
                character.getAllies().add(existingCharacter);
                existingCharacter.getAllies().add(character);
            }

        }
        /* end of BAD CODE */

        characters.add(character);

        character.setListener(this);
        applyObstaclesTo(character);
        applyGravityTo(character);
        character.getPosition().set(positionX, positionY);
    }

    public float getFloorSurfacePosition() {
        return floor.y + floor.height;
    }


    @Override
    public float findAbsoluteDistanceToLeftWall(AbstractCharacter character) {
        return (character.getHitBox().x + character.getHitBox().width / 2) - (leftWall.x + leftWall.width);
    }

    @Override
    public float findAbsoluteDistanceToRightWall(AbstractCharacter character) {
        return rightWall.x - (character.getHitBox().x + character.getHitBox().width / 2);
    }

    @Override
    public void onCharacterDead(AbstractCharacter character) {
        characters.removeValue(character, true);
        final int characterCount = characters.size;
        for (int i = 0; i < characterCount; i++) {
            final AbstractCharacter otherCharacter = characters.get(i);
            otherCharacter.getAllies().removeValue(character, true);
            otherCharacter.getEnemies().removeValue(character, true);
            if (otherCharacter.getTarget() == character) {
                otherCharacter.setTarget(null);
            }
        }
    }

    public Array<AbstractCharacter> getCharacters() {
        return characters;
    }
}
