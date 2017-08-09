package th.skyousuke.braveland;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import th.skyousuke.braveland.object.AbstractCharacter;


public class HotBox {

    private Rectangle box = new Rectangle();
    private Array<AbstractCharacter> targets = new Array<>();

    private final float initRelativeX;
    private final ViewDirection initKnockBackDirection;

    private float relativeX;
    private float relativeY;

    private Damage damage;

    private int maxTarget = 1;
    private int maxHitPerTarget = 1;

    private float lifespan;
    private float hitInterval;

    private HotBoxListener listener;

    private ObjectMap<AbstractCharacter, HotBoxStatistics> allStatistics = new ObjectMap<>();

    public HotBox(float relativeX, float relativeY, float width, float height) {
        this(relativeX, relativeY, width, height, new Damage());
    }

    public HotBox(float relativeX, float relativeY, float width, float height, Damage damage) {
        initRelativeX = relativeX;
        initKnockBackDirection = damage.getKnockBackDirection();

        this.relativeY = relativeY;
        this.damage = damage;
        box.width = width;
        box.height = height;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public void setMaxTarget(int maxTarget) {
        this.maxTarget = maxTarget;
    }

    public void setMaxHitPerTarget(int maxHitPerTarget) {
        this.maxHitPerTarget = maxHitPerTarget;
    }

    public void setHitInterval(float hitInterval) {
        this.hitInterval = hitInterval;
    }

    public void debug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(box.x, box.y, box.width, box.height);
    }

    public void updateBoxPosition(float ownerX, float ownerY) {
        box.x = relativeX + ownerX;
        box.y = relativeY + ownerY;
    }

    public void setFlipX(boolean flipX, float flipXOffset) {
        if (flipX) {
            relativeX = flipXOffset - box.width - initRelativeX;
            damage.setKnockBackDirection(initKnockBackDirection.getOpposite());
        } else {
            relativeX = initRelativeX;
            damage.setKnockBackDirection(initKnockBackDirection);
        }
    }

    public Array<AbstractCharacter> getTargets() {
        return targets;
    }

    public void update(float deltaTime) {
        lifespan += deltaTime;
        for (final AbstractCharacter target : targets) {
            if (!target.isAlive()) {
                targets.removeValue(target, true);
                continue;
            }
            if (box.overlaps(target.getHitBox())) {
                HotBoxStatistics statistics = allStatistics.get(target);
                if (statistics == null && allStatistics.size < maxTarget) {
                    statistics = new HotBoxStatistics();
                    allStatistics.put(target, statistics);
                    hit(target, statistics);
                } else if (statistics != null && (lifespan - statistics.lastHitTime >= hitInterval)
                        && (statistics.hitCount < maxHitPerTarget)) {
                    hit(target, statistics);
                }
            }
        }
    }

    public Rectangle getBox() {
        return box;
    }

    public void setListener(HotBoxListener listener) {
        this.listener = listener;
    }

    private void hit(AbstractCharacter character, HotBoxStatistics statistics) {
        character.takeDamage(damage);
        statistics.lastHitTime = lifespan;
        statistics.hitCount++;
        if (listener != null)
            listener.onHit(this, character);
    }

    private static class HotBoxStatistics {

        private float lastHitTime;
        private int hitCount;

    }

}
