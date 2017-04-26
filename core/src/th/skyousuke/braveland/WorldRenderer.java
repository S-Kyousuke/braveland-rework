package th.skyousuke.braveland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldRenderer implements Disposable {

    private WorldController worldController;

    private OrthographicCamera camera = new OrthographicCamera();
    private OrthographicCamera uiCamera = new OrthographicCamera();

    private Viewport viewport = new FitViewport(960, 540, camera);
    private Viewport uiViewport = new FitViewport(960, 540, uiCamera);

    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
    }

    public void render() {
        // clear screen
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update camera
        uiCamera.update();
        worldController.getCameraHelper().applyTo(camera);

        // game world drawing
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldController.getLevel().draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        worldController.getLevel().debug(shapeRenderer);
        shapeRenderer.end();

        // ui drawing
        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();
        worldController.getLevel().drawUi(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        worldController.getLevel().debugUi(shapeRenderer);
        shapeRenderer.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        uiViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
