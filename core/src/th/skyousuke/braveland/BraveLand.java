package th.skyousuke.braveland;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxAI;
import th.skyousuke.braveland.screen.MenuScreen;

public class BraveLand extends Game {
	
	@Override
	public void create () {
	    Assets.instance.init();
        Assets.instance.music.setLooping(true);
	    Assets.instance.music.play();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
	}

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }
}
