package th.skyousuke.braveland.level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CameraHelper;
import th.skyousuke.braveland.player.Player;

public class Intro extends AbstractLevel {

    private Player player = new Player();
    private GlyphLayout glyphLayout = new GlyphLayout();
    private float dialogTextWidth;
    private String dialogText = "";

    public Intro() {
        mapBackground = Assets.instance.mapIntroTexture;
        floor = new Rectangle(0, 150, mapBackground.getWidth(), 50);

        width = mapBackground.getWidth();
        height = mapBackground.getHeight();

        setUpWall();

        player.getObstacleBoxes().add(floor);
        player.getObstacleBoxes().add(leftWall);
        player.getObstacleBoxes().add(rightWall);

        player.getAcceleration().y = -1200;

        characters.add(player);
    }

    @Override
    public void init(CameraHelper cameraHelper) {
        player.getPosition().set(0 ,200);
        //getPlayerListener().onPlayerChanged(player);

        cameraHelper.setTarget(player);
        cameraHelper.setLevelDimension(width, height);
        cameraHelper.setPositionToTarget();
    }

    @Override
    public void update(float delaTime) {
        super.update(delaTime);
        player.getVelocity().x = 120;

        if (player.getPosition().x < mapBackground.getWidth() / 3) {
            updateDialogText("ย้อนเวลากลับไป 20 ปีก่อน..");
        } else if (player.getPosition().x < mapBackground.getWidth() * 2 / 3) {
            updateDialogText("เหล่ามอนสเตอร์ได้รุกรานดินแดนมนุษย์");
        } else if (player.getPosition().x < mapBackground.getWidth()) {
            updateDialogText("สงครามยังคงดำเนินต่อไป...");
        }

        if (player.getPosition().x >= mapBackground.getWidth() - player.getHitBox().width) {
            getLevelListener().onLevelChanged(Level.TOWN);
        }
    }

    @Override
    public void drawUi(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, 960, 80);
        shapeRenderer.rect(0, 460, 960, 80);
        shapeRenderer.end();

        batch.begin();
        Assets.instance.font.draw(batch, dialogText, 480 - dialogTextWidth / 2, 48);
        batch.end();
    }

    private void updateDialogText(String text) {
        dialogText = text;
        glyphLayout.setText(Assets.instance.font, text);
        dialogTextWidth = glyphLayout.width;
    }

    //    public void debug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);
//        player.debug(shapeRenderer);
//    }

}
