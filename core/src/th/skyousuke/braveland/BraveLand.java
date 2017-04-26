package th.skyousuke.braveland;

import com.badlogic.gdx.Game;
import th.skyousuke.braveland.screen.MenuScreen;
import th.skyousuke.braveland.utils.Assets;

public class BraveLand extends Game {

    @Override
    public void create() {
        Assets.instance.init();
        Assets.instance.music.setLooping(true);
        Assets.instance.music.play();

        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }
}
