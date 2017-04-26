package th.skyousuke.braveland.object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public interface ObstacleAware {

    Array<Rectangle> getObstacleBoxes();

}
