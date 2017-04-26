package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.object.player.Player;

public class Five extends AbstractLevel {

    private Player player = new Player();

    public Five() {
        super(Assets.instance.mapFiveTexture);
        addCharacter(player, 500, 500);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 170, getWidth(), 50);
    }

}
