package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.object.player.Player;

public class Six extends AbstractLevel {

    private Player player = new Player();

    public Six() {
        super(Assets.instance.mapSixTexture);
        addCharacter(player, 500, 500);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 109, getWidth(), 50);
    }

    //    public void debug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);
//        player.debug(shapeRenderer);
//    }

}
