package th.skyousuke.braveland;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class KeyFrameData {

    public TextureRegion region;
    public float offsetX;
    public float offsetY;
    public float hitBoxWidth;
    public float hitBoxHeight;
    public Array<HotBox> hotBoxes = new Array<HotBox>();

}
