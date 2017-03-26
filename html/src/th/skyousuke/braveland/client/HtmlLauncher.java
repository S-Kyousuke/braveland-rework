package th.skyousuke.braveland.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import th.skyousuke.braveland.BraveLand;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(960, 540);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new BraveLand();
        }
}