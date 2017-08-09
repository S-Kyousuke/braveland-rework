package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.object.npc.NpcFightingAiState;
import th.skyousuke.braveland.object.npc.monster.monster1.Monster1;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;

public class One extends AbstractLevel {

    private static final int MONSTER_COUNT = 1;

    private Player player = new Player();
//    private Partner partner;

    public One() {
        super(Assets.instance.mapOneTexture);
    }

    @Override
    protected Rectangle makeFloor() {
        return new Rectangle(0, 100, getWidth(), 53);
    }

    @Override
    public void init(CameraHelper cameraHelper) {
        super.init(cameraHelper);

        getPlayerListener().onPlayerChanged(player);
        player.getEnemies().clear();

        player.setHealth(1);

//        partner = PartnerUtils.makeRandomPartner();

        for (int i = 0; i < MONSTER_COUNT; i++) {
            Monster1 monster = new Monster1();

            monster.getEnemies().add(player);
//            monster.getEnemies().add(partner);

            player.getEnemies().add(monster);
//            partner.getEnemies().add(monster);

            monster.getAiStateMachine().changeState(NpcFightingAiState.WALK_LEFT);

            addCharacter(monster, 400f + i * 200f, getFloorSurfacePosition());
        }

        addCharacter(player, 50, getFloorSurfacePosition());
//        addCharacter(partner, 100, getFloorSurfacePosition());


        cameraHelper.setTarget(player);
        cameraHelper.setPositionToTarget();

//        partner.getAiStateMachine().changeState(NpcFightingAiState.WALK_RIGHT);
    }
}
