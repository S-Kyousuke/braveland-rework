package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ai.GdxAI;
import th.skyousuke.braveland.WorldController;
import th.skyousuke.braveland.WorldRenderer;

public class GameScreen extends AbstractScreen {

    private static final int MIN_FPS = 10;

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
        float adjustedDelta = Math.min(delta, 1f / MIN_FPS);

        if (!pause) {
            GdxAI.getTimepiece().update(adjustedDelta);
            worldController.update(adjustedDelta);
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
