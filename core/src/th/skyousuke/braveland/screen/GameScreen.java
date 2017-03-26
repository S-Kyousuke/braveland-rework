package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import th.skyousuke.braveland.WorldController;
import th.skyousuke.braveland.WorldRenderer;

public class GameScreen extends AbstractGameScreen {

    private WorldController worldController = new WorldController();
    private WorldRenderer worldRenderer = new WorldRenderer(worldController);

    public GameScreen(Game game) {
        super(game);
    }

    public GameScreen(Game game, boolean skipIntro) {
        super(game);
        if (skipIntro) {
            worldController.skipIntro();
        }
    }

    @Override
    public void render(float delta) {
        if (!pause) {
            worldController.update(delta);
            worldRenderer.render();
        } else if (ready) {
            pause = false;
        }
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }
}
