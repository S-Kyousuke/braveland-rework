package th.skyousuke.braveland.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import th.skyousuke.braveland.BraveLand;

public class DesktopLauncher {

    private DesktopLauncher() {
    }

    public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 960;
		config.height = 540;
		config.backgroundFPS = -1;
		new LwjglApplication(new BraveLand(), config);
	}
}
