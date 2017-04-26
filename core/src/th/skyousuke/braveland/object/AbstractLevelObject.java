package th.skyousuke.braveland.object;


import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.HotBox;


public abstract class AbstractLevelObject extends AbstractGameObject implements ObstacleAware {

    protected boolean alive = true;
    protected Array<HotBox> hotBoxes = new Array<>();

    public AbstractLevelObject(float width, float height) {
        super(width, height);
    }

    public boolean isAlive() {
        return alive;
    }

    public Array<HotBox> getHotBoxes() {
        return hotBoxes;
    }
}
