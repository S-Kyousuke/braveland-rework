package th.skyousuke.braveland.level;


import com.badlogic.gdx.math.Rectangle;
import th.skyousuke.braveland.Assets;
import th.skyousuke.braveland.player.Player;

public class Two extends AbstractLevel {

    private Player player = new Player();

    public Two() {
        mapBackground = Assets.instance.mapTwoTexture;
        floor = new Rectangle(0, 110, mapBackground.getWidth(), 52);

        width = mapBackground.getWidth();
        height = mapBackground.getHeight();

        setUpWall();

        player.getObstacleBoxes().add(floor);
        player.getObstacleBoxes().add(leftWall);
        player.getObstacleBoxes().add(rightWall);

        player.getAcceleration().y = -1200;

        player.getPosition().set(500 ,500);

        characters.add(player);
    }

//    public void debug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.rect(floor.x, floor.y, floor.width, floor.height);
//        player.debug(shapeRenderer);
//    }

}
