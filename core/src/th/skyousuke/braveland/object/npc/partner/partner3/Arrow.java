package th.skyousuke.braveland.object.npc.partner.partner3;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import th.skyousuke.braveland.HotBox;
import th.skyousuke.braveland.HotBoxListener;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.object.AbstractLevelObject;
import th.skyousuke.braveland.utils.Assets;

public class Arrow extends AbstractLevelObject implements HotBoxListener {

    private static final int WIDTH = 40;
    private static final int HEIGHT = 7;

    private static final float AIR_FRICTION = 10f;

    private Array<Rectangle> obstacleBoxes = new Array<>();

    private boolean flipX;

    private HotBox hotBox;
    private int remainingHit = 1;

    public Arrow(float positionX, float positionY, float velocityX, float velocityY, boolean flipX) {
        super(WIDTH, HEIGHT);
        getPosition().set(positionX, positionY);
        getVelocity().set(velocityX, velocityY);

        getFriction().set(AIR_FRICTION, AIR_FRICTION);

        this.flipX = flipX;

        if (!flipX) {
            hotBox = new HotBox(0, 0, WIDTH, HEIGHT);
        } else {
            hotBox = new HotBox(-WIDTH, 0, WIDTH, HEIGHT);
        }
        updateHotBoxPosition();
        hotBox.setListener(this);

        hotBoxes.add(hotBox);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (getVelocity().isZero()) {
            alive = false;
        }
    }

    @Override
    protected void onAfterMoveX() {
        updateHotBoxPosition();
        for (Rectangle obstacleBox : obstacleBoxes) {
            if (obstacleBox.overlaps(hotBox.getBox())) {
                alive = false;
            }
        }
    }

    @Override
    protected void onAfterMoveY() {
        updateHotBoxPosition();
        for (Rectangle obstacleBox : obstacleBoxes) {
            if (obstacleBox.overlaps(hotBox.getBox())) {
                alive = false;
            }
        }
    }

    public void setRemainingHit(int remainingHit) {
        this.remainingHit = remainingHit;
    }

    private void updateHotBoxPosition() {
        hotBox.updateBoxPosition(getPosition().x, getPosition().y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!flipX) {
            batch.draw(Assets.instance.arrowTexture,
                    getPosition().x, getPosition().y, WIDTH, HEIGHT);
        } else {
            batch.draw(Assets.instance.arrowTexture,
                    getPosition().x, getPosition().y, -WIDTH, HEIGHT);
        }

    }

    @Override
    public void debug(ShapeRenderer shapeRenderer) {
        hotBox.debug(shapeRenderer);
    }

    public HotBox getHotBox() {
        return hotBox;
    }

    @Override
    public Array<Rectangle> getObstacleBoxes() {
        return obstacleBoxes;
    }

    @Override
    public void onHit(HotBox hotBox, AbstractCharacter character) {
        if (remainingHit == 1)
            alive = false;
        else {
            remainingHit--;
        }
    }
}
