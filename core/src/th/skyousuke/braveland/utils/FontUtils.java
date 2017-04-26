package th.skyousuke.braveland.utils;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class FontUtils {

    private static GlyphLayout glyphLayout = new GlyphLayout();

    private FontUtils() {
    }

    public static float getTextWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public static float getTextHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.height;
    }

    public static GlyphLayout getGlyphLayout(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout;
    }


}
