package th.skyousuke.braveland;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ObjectMap;


public class HotBox {

    private Rectangle box = new Rectangle();

    private float relativeX;
    private float relativeY;

    private float currentDamage;
    private int remainingHit;

    private float knockBackSpeed;
    private ViewDirection knockBackDirection;

    private ObjectMap<AbstractCharacter, Integer> affectCounts = new ObjectMap<AbstractCharacter, Integer>();

    public HotBox(float relativeX, float relativeY, float width, float height,
                  float initDamage, int initHit, float knockBackSpeed, ViewDirection knockBackDirection) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
//        this.width = width;
//        this.height = height;

        currentDamage = initDamage;
        remainingHit = initHit;
        this.knockBackSpeed = knockBackSpeed;
        this.knockBackDirection = knockBackDirection;

        box.width = width;
        box.height = height;
    }

    public void damageTo(AbstractCharacter character, float damageDrop) {
        if (remainingHit == 0)
            return;
        character.takeDamage(currentDamage, knockBackSpeed, knockBackDirection);
        Integer affectCount = affectCounts.get(character);
        if (affectCount == null) {
            affectCounts.put(character, 1);
        } else {
            affectCounts.put(character, affectCount + 1);
        }
        remainingHit--;
        currentDamage -= damageDrop;
        if (currentDamage < 0)
            currentDamage = 0;
    }

    public boolean overlaps(AbstractCharacter character) {
        return box.overlaps(character.getHitBox());
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(box.x, box.y, box.width, box.height);
    }

    public Rectangle getBox() {
        return box;
    }

    public void updateBoxPosition(float ownerX, float ownerY) {
        box.x =  relativeX + ownerX;
        box.y =  relativeY + ownerY;
    }
}
