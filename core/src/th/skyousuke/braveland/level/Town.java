package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;

public class Town extends AbstractLevel {

    private Player player = new Player();

    public Town() {
        super(Assets.instance.mapTownTexture);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 113, getWidth(), 50);
    }

    @Override
    public void init(CameraHelper cameraHelper) {
        super.init(cameraHelper);

        addCharacter(player, 500, getFloorSurfacePosition());

        cameraHelper.setTarget(player);
        cameraHelper.setPositionToTarget();

        getPlayerListener().onPlayerChanged(player);
    }

    @Override
    public void onSpecialKeyPressed(int command) {
        switch (command) {
            case 0:
            case 1:
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
        }
    }


}
