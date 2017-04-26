package th.skyousuke.braveland;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import th.skyousuke.braveland.level.AbstractLevel;
import th.skyousuke.braveland.level.Level;
import th.skyousuke.braveland.level.LevelListener;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.object.player.PlayerChangeListener;
import th.skyousuke.braveland.utils.CameraHelper;

public class WorldController implements PlayerChangeListener, LevelListener {

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
    }

    // TODO: for debug only. should be remove in production code.
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            setLevel(currentLevel.getNext());
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            currentLevel.getLevel().onSpecialKeyPressed(0);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            currentLevel.getLevel().onSpecialKeyPressed(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            currentLevel.getLevel().onSpecialKeyPressed(2);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) {
            currentLevel.getLevel().onSpecialKeyPressed(3);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) {
            currentLevel.getLevel().onSpecialKeyPressed(4);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
            currentLevel.getLevel().onSpecialKeyPressed(5);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
            currentLevel.getLevel().onSpecialKeyPressed(6);
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
    public void onChangeLevelRequest(Level newLevel) {
        this.nextLevel = newLevel;
    }

    public void skipIntro() {
        setLevel(Level.TOWN);
    }
}
