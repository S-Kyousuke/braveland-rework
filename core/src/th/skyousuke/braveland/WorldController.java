package th.skyousuke.braveland;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import th.skyousuke.braveland.level.AbstractLevel;
import th.skyousuke.braveland.level.Level;
import th.skyousuke.braveland.player.Player;

public class WorldController implements PlayerChangeListener, LevelChangeListener {

    private CameraHelper cameraHelper = new CameraHelper();

    private Level currentLevel;
    private Level nextLevel;

    private Player player;

    public WorldController() {
        setLevel(Level.INTRO);
    }

    private void setLevel(Level level) {
        if (currentLevel != null) {
            currentLevel.getLevel().setPlayerListener(null);
            currentLevel.getLevel().setLevelListener(null);
        }
        currentLevel = level;
        currentLevel.getLevel().setPlayerListener(this);
        currentLevel.getLevel().setLevelListener(this);
        currentLevel.getLevel().init(cameraHelper);
    }

    public void update(float deltaTime) {
        if (nextLevel != null) {
            setLevel(nextLevel);
            nextLevel = null;
        }

        handleInput();
        if (player != null) {
            handlePlayerInput();
        }
        currentLevel.getLevel().update(deltaTime);
        cameraHelper.update();
    }

    public CameraHelper getCameraHelper() {
        return cameraHelper;
    }

    private void handlePlayerInput() {
        player.getActionStateMachine().getCurrentState().handleInput(player);


//        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
//            player.getVelocity().y = 380;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            player.setViewDirection(ViewDirection.RIGHT);
//            player.getVelocity().x = 200;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            player.setViewDirection(ViewDirection.LEFT);
//            player.getVelocity().x = -200;
//        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            setLevel(currentLevel.getNext());
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            currentLevel.getLevel().onSpecialKeyPressed();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            cameraHelper.addZoom(-0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            cameraHelper.addZoom(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            cameraHelper.setZoom(1f);
        }
    }

    public AbstractLevel getLevel() {
        return currentLevel.getLevel();
    }

    @Override
    public void onPlayerChanged(Player player) {
        this.player = player;
    }

    @Override
    public void onLevelChanged(Level level) {
        this.nextLevel = level;
    }

    public void skipIntro() {
        setLevel(Level.TOWN);
    }
}
