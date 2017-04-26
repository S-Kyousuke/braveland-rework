package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen {

    protected final Game game;
    protected boolean pause;
    protected boolean ready;

    public AbstractScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        pause = true;
        ready = false;
    }

    @Override
    public void resume() {
        ready = true;
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
