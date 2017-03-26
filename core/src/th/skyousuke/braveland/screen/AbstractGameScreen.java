package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class AbstractGameScreen implements Screen {

    protected final Game game;
    protected boolean pause;
    protected boolean ready;

    public AbstractGameScreen(Game game) {
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
        Gdx.app.log("hide()", "called!");
    }

    @Override
    public void dispose() {
        Gdx.app.log("dispose()", "called!");
    }

}
