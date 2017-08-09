package th.skyousuke.braveland.level;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;
import th.skyousuke.braveland.utils.FontUtils;

public class Intro extends AbstractLevel {

    private Player player = new Player();
    private float dialogTextWidth;
    private String dialogText = "";

    public Intro() {
        super(Assets.instance.mapIntroTexture);
        addCharacter(player, 0, 0);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 150, getWidth(), 50);
    }

    @Override
    public void init(CameraHelper cameraHelper) {
        player.getPosition().set(0, 200);

        cameraHelper.setTarget(player);
        cameraHelper.setCameraBound(getWidth(), getHeight());
        cameraHelper.setPositionToTarget();
    }

    @Override
    public void update(float delaTime) {
        super.update(delaTime);
        player.getVelocity().x = 120;

        if (player.getPosition().x < getWidth() / 3) {
            updateDialogText("ย้อนเวลากลับไป 20 ปีก่อน..");
        } else if (player.getPosition().x < getWidth() * 2 / 3) {
            updateDialogText("เหล่ามอนสเตอร์ได้รุกรานดินแดนมนุษย์");
        } else if (player.getPosition().x < getWidth()) {
            updateDialogText("สงครามยังคงดำเนินต่อไป...");
        }

        if (player.getPosition().x >= getWidth() - player.getHitBox().width) {
            getLevelListener().onChangeLevelRequest(Level.TOWN);
        }
    }

    @Override
    public void drawUi(SpriteBatch batch) {
        batch.setColor(Color.BLACK);
        batch.draw(Assets.instance.background, 0, 0, 960, 80);
        batch.draw(Assets.instance.background, 0, 460, 960, 80);

        batch.setColor(Color.WHITE);
        Assets.instance.font.draw(batch, dialogText, 480 - dialogTextWidth / 2, 48);
    }

    private void updateDialogText(String text) {
        dialogText = text;
        dialogTextWidth = FontUtils.getTextWidth(Assets.instance.font, text);
    }

    //    public void debug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);
//        player.debug(shapeRenderer);
//    }

}
