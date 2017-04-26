package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.object.npc.monster.monster12.Monster12;
import th.skyousuke.braveland.object.npc.partner.partner1.Partner1;
import th.skyousuke.braveland.object.npc.partner.partner2.Partner2;
import th.skyousuke.braveland.object.npc.partner.partner3.Partner3;
import th.skyousuke.braveland.object.npc.partner.partner4.Partner4;
import th.skyousuke.braveland.object.npc.partner.partner5.Partner5;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;

public class Town extends AbstractLevel {

    private Player player = new Player();

    private Partner1 partner1 = new Partner1();
    private Partner2 partner2 = new Partner2();
    private Partner3 partner3 = new Partner3();
    private Partner4 partner4 = new Partner4();
    private Partner5 partner5 = new Partner5();

    private Monster12 monster = new Monster12();

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

        addCharacter(monster, getWidth() - 400, getFloorSurfacePosition());
        monster.getEnemies().add(player);

        addCharacter(partner1, 1000, getFloorSurfacePosition());
        addCharacter(partner2, 800, getFloorSurfacePosition());
        addCharacter(partner3, 600, getFloorSurfacePosition());
        addCharacter(partner4, 400, getFloorSurfacePosition());
        addCharacter(partner5, 200, getFloorSurfacePosition());

        addCharacter(player, getWidth() - 500, getFloorSurfacePosition());
        player.getEnemies().add(monster);

        cameraHelper.setTarget(player);
        cameraHelper.setPositionToTarget();

        getPlayerListener().onPlayerChanged(player);
    }

    @Override
    public void onSpecialKeyPressed(int command) {
        switch (command) {
            case 0:
                monster.setViewDirection(monster.getViewDirection().getOpposite());
                break;
            case 1:
                monster.attack();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
        }
    }


}
