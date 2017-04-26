package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.object.player.Player;

public class Two extends AbstractLevel {

    private Player player = new Player();

    public Two() {
        super(Assets.instance.mapTwoTexture);
        addCharacter(player, 500, 500);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 110, getWidth(), 52);
    }
}
