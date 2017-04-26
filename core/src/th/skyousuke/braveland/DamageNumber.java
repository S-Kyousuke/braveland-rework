package th.skyousuke.braveland;

import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CompareUtils;

public class DamageNumber {

    private static final float LIFESPAN_SECOND = 0.75f;
    private static final float DELTA_Y_SPEED_PER_SECOND = 100;

    private BitmapFontCache fontCache;

    private float deltaTime;

    public DamageNumber(int damage, float x, float y) {
        if (damage > 0) {
            fontCache = new BitmapFontCache(Assets.instance.damageFont, false);
            fontCache.setText(String.valueOf(damage), x, y);
        } else {
            fontCache = new BitmapFontCache(Assets.instance.healFont, false);
            fontCache.setText(String.valueOf(Math.abs(damage)), x, y);
        }
    }

    public boolean update(float delta) {
        deltaTime += delta / LIFESPAN_SECOND;
        float alpha = (float) ((10001 - Math.pow(10000, deltaTime)) / 10000);
        if (alpha < 0) {
            alpha = 0;
        }
        fontCache.setAlphas(alpha);
        fontCache.setPosition(fontCache.getX(), fontCache.getY() + DELTA_Y_SPEED_PER_SECOND * delta);
        return !CompareUtils.floatEquals(alpha, 0);
    }

    public void draw(SpriteBatch batch) {
        fontCache.draw(batch);
    }

}
