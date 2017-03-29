package th.skyousuke.braveland;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class HotBox {

    private Rectangle box = new Rectangle();
    private Array<AbstractCharacter> targets = new Array<AbstractCharacter>();

    private float relativeX;
    private float relativeY;

    private float currentDamage;
    private int remainingHit;

    private float knockBackSpeed;
    private ViewDirection knockBackDirection;

    private ObjectMap<AbstractCharacter, Integer> affectCounts = new ObjectMap<AbstractCharacter, Integer>();

    public HotBox(float relativeX, float relativeY, float width, float height,
                  float initDamage, int initHit) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;

        currentDamage = initDamage;
        remainingHit = initHit;

        box.width = width;
        box.height = height;
    }

    public void setKnockBackSpeed(float knockBackSpeed) {
        this.knockBackSpeed = knockBackSpeed;
    }

    public void setKnockBackDirection(ViewDirection knockBackDirection) {
        this.knockBackDirection = knockBackDirection;
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

    public boolean canAffectOn(AbstractCharacter character) {
        return targets.contains(character, true) && box.overlaps(character.getHitBox());
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

    public float getRelativeX() {
        return relativeX;
    }

    public float getRelativeY() {
        return relativeY;
    }

    public float getCurrentDamage() {
        return currentDamage;
    }

    public int getRemainingHit() {
        return remainingHit;
    }

    public float getKnockBackSpeed() {
        return knockBackSpeed;
    }

    public ViewDirection getKnockBackDirection() {
        return knockBackDirection;
    }

    public ObjectMap<AbstractCharacter, Integer> getAffectCounts() {
        return affectCounts;
    }

    public void flipRightToLeft(float flipXOffset) {
        relativeX =  flipXOffset - relativeX - box.width;
        if (knockBackDirection == ViewDirection.LEFT) {
            knockBackDirection = ViewDirection.RIGHT;
        } else {
            knockBackDirection = ViewDirection.LEFT;
        }
    }

    public Array<AbstractCharacter> getTargets() {
        return targets;
    }
}
