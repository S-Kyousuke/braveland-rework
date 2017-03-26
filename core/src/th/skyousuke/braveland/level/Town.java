package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.CameraHelper;
import th.skyousuke.braveland.npc.Partner1;
import th.skyousuke.braveland.npc.monster.Monster1;
import th.skyousuke.braveland.npc.partner.PartnerAiState;
import th.skyousuke.braveland.player.Player;

public class Town extends AbstractLevel {

    private Player player = new Player();
    private Partner1 partner1 = new Partner1();
    private Monster1 monster1 = new Monster1();;
    private Monster1 secondMonster1 = new Monster1();

    public Town() {
        mapBackground = Assets.instance.mapTownTexture;
        floor = new Rectangle(0, 113, mapBackground.getWidth(), 50);

        width = mapBackground.getWidth();
        height = mapBackground.getHeight();

        setUpWall();

        player.getObstacleBoxes().add(floor);
        player.getObstacleBoxes().add(leftWall);
        player.getObstacleBoxes().add(rightWall);
        player.getAcceleration().y = -1200;
        player.getPosition().set(500 ,500);

        partner1.getObstacleBoxes().add(floor);
        partner1.getObstacleBoxes().add(leftWall);
        partner1.getObstacleBoxes().add(rightWall);
        partner1.getAcceleration().y = -1200;
        partner1.getPosition().set(300, 500);
        partner1.setTarget(secondMonster1);
        //partner1.setViewDirection(ViewDirection.LEFT);

        monster1.getObstacleBoxes().add(floor);
        monster1.getObstacleBoxes().add(leftWall);
        monster1.getObstacleBoxes().add(rightWall);
        monster1.getAcceleration().y = -1200;
        monster1.getPosition().set(600, 500);
        monster1.setTarget(player);

        secondMonster1.getObstacleBoxes().add(floor);
        secondMonster1.getObstacleBoxes().add(leftWall);
        secondMonster1.getObstacleBoxes().add(rightWall);
        secondMonster1.getAcceleration().y = -1200;
        secondMonster1.getPosition().set(800, 500);
        secondMonster1.setTarget(partner1);

        objects.add(player);
        objects.add(partner1);
        objects.add(monster1);
        objects.add(secondMonster1);

        partner1.getAiStateMachine().changeState(PartnerAiState.ALERT);
    }

    @Override
    public void init(CameraHelper cameraHelper) {
        super.init(cameraHelper);

        cameraHelper.setTarget(player);
        cameraHelper.setLevelDimension(width, height);
        cameraHelper.setPositionToTarget();

        getPlayerListener().onPlayerChanged(player);
    }

    @Override
    public void onSpecialKeyPressed() {
        if (partner1.getAiStateMachine().getCurrentState() == PartnerAiState.NORMAL) {
            partner1.getAiStateMachine().changeState(PartnerAiState.ALERT);
        } else {
            partner1.getAiStateMachine().changeState(PartnerAiState.NORMAL);
        }
    }

    //    public void debug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);
//        player.debug(shapeRenderer);
//    }

}
