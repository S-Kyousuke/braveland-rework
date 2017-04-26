package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import th.skyousuke.braveland.utils.Assets;


public class MenuScreen extends AbstractScreen {

    private SpriteBatch batch = new SpriteBatch();
    private Viewport viewport = new FitViewport(960, 540);
    private Stage uiStage = new Stage(viewport, batch);

    public MenuScreen(final Game game) {
        super(game);

        final Table table = new Table();
        final TextButton newGameButton = new TextButton("NEW GAME", Assets.instance.skin);
        final TextButton loadGameButton = new TextButton("LOAD GAME", Assets.instance.skin);
        final TextButton exitButton = new TextButton("EXIT", Assets.instance.skin);

        table.add(newGameButton).width(160);
        table.row();
        table.add(loadGameButton).width(160).spaceTop(20);
        table.row();
        table.add(exitButton).width(160).spaceTop(20);
        table.pack();

        table.setPosition(960 / 2 - table.getWidth() / 2, 120);

        uiStage.addActor(table);

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, true));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void render(float delta) {
        uiStage.act(delta);

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.getCamera().update();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(Assets.instance.menuBackground, 0, 0);
        batch.end();

        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        batch.dispose();
        uiStage.dispose();
    }
}
