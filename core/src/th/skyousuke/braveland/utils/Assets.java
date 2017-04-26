package th.skyousuke.braveland.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final Assets instance = new Assets();

    // Map background
    public Texture mapIntroTexture;
    public Texture mapTownTexture;
    public Texture mapOneTexture;
    public Texture mapTwoTexture;
    public Texture mapThreeTexture;
    public Texture mapFourTexture;
    public Texture mapFiveTexture;
    public Texture mapSixTexture;
    public Texture mapBossTexture;

    // object
    public Texture arrowTexture;

    // character atlas
    public TextureAtlas playerAtlas;

    public TextureAtlas partner1Atlas;
    public TextureAtlas partner2Atlas;
    public TextureAtlas partner3Atlas;
    public TextureAtlas partner4Atlas;
    public TextureAtlas partner5Atlas;

    public TextureAtlas monster1Atlas;
    public TextureAtlas monster2Atlas;
    public TextureAtlas monster3Atlas;
    public TextureAtlas monster4Atlas;
    public TextureAtlas monster5Atlas;
    public TextureAtlas monster6Atlas;
    public TextureAtlas monster7Atlas;
    public TextureAtlas monster8Atlas;
    public TextureAtlas monster9Atlas;
    public TextureAtlas monster10Atlas;
    public TextureAtlas monster11Atlas;
    public TextureAtlas monster12Atlas;


    // menu screen
    public Texture menuBackground;

    // background
    public Texture background;

    // font
    public BitmapFont font;
    public BitmapFont damageFont;
    public BitmapFont healFont;

    // music
    public Music music;

    // sounds
    public Sound swordStrike;
    public Sound bowFire;
    public Sound hit;

    // skins
    public Skin skin;

    private AssetManager manager;

    private Assets() {
    }

    public void init() {
        manager = new AssetManager();
        manager.setErrorListener(this);

        manager.load("map/intro.jpg", Texture.class);
        manager.load("map/town.jpg", Texture.class);
        manager.load("map/1.jpg", Texture.class);
        manager.load("map/2.jpg", Texture.class);
        manager.load("map/3.jpg", Texture.class);
        manager.load("map/4.jpg", Texture.class);
        manager.load("map/5.jpg", Texture.class);
        manager.load("map/6.jpg", Texture.class);
        manager.load("map/boss.jpg", Texture.class);

        manager.load("menu_screen.png", Texture.class);

        manager.load("music/music.mp3", Music.class);

        manager.load("uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin.atlas"));

        manager.load("font.fnt", BitmapFont.class);
        manager.load("damage.fnt", BitmapFont.class);
        manager.load("heal.fnt", BitmapFont.class);

        manager.load("character/player.atlas", TextureAtlas.class);

        manager.load("character/partner1.atlas", TextureAtlas.class);
        manager.load("character/partner2.atlas", TextureAtlas.class);
        manager.load("character/partner3.atlas", TextureAtlas.class);
        manager.load("character/partner4.atlas", TextureAtlas.class);
        manager.load("character/partner5.atlas", TextureAtlas.class);

        manager.load("character/monster1.atlas", TextureAtlas.class);
        manager.load("character/monster2.atlas", TextureAtlas.class);
        manager.load("character/monster3.atlas", TextureAtlas.class);
        manager.load("character/monster4.atlas", TextureAtlas.class);
        manager.load("character/monster5.atlas", TextureAtlas.class);
        manager.load("character/monster6.atlas", TextureAtlas.class);
        manager.load("character/monster7.atlas", TextureAtlas.class);
        manager.load("character/monster8.atlas", TextureAtlas.class);
        manager.load("character/monster9.atlas", TextureAtlas.class);
        manager.load("character/monster10.atlas", TextureAtlas.class);
        manager.load("character/monster11.atlas", TextureAtlas.class);
        manager.load("character/monster12.atlas", TextureAtlas.class);

        manager.load("sounds/sword-strike.mp3", Sound.class);
        manager.load("sounds/bow-fire.mp3", Sound.class);
        manager.load("sounds/hit.mp3", Sound.class);

        manager.load("arrow.png", Texture.class);

        manager.load("white-bg.png", Texture.class);

        manager.finishLoading();

        mapIntroTexture = manager.get("map/intro.jpg");
        mapTownTexture = manager.get("map/town.jpg");
        mapOneTexture = manager.get("map/1.jpg");
        mapTwoTexture = manager.get("map/2.jpg");
        mapThreeTexture = manager.get("map/3.jpg");
        mapFourTexture = manager.get("map/4.jpg");
        mapFiveTexture = manager.get("map/5.jpg");
        mapSixTexture = manager.get("map/6.jpg");
        mapBossTexture = manager.get("map/boss.jpg");

        music = manager.get("music/music.mp3");

        menuBackground = manager.get("menu_screen.png");
        menuBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        skin = manager.get("uiskin.json");

        font = manager.get("font.fnt");
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        damageFont = manager.get("damage.fnt");
        damageFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        healFont = manager.get("heal.fnt");
        healFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        playerAtlas = manager.get("character/player.atlas");

        partner1Atlas = manager.get("character/partner1.atlas");
        partner2Atlas = manager.get("character/partner2.atlas");
        partner3Atlas = manager.get("character/partner3.atlas");
        partner4Atlas = manager.get("character/partner4.atlas");
        partner5Atlas = manager.get("character/partner5.atlas");

        monster1Atlas = manager.get("character/monster1.atlas");
        monster2Atlas = manager.get("character/monster2.atlas");
        monster3Atlas = manager.get("character/monster3.atlas");
        monster4Atlas = manager.get("character/monster4.atlas");
        monster5Atlas = manager.get("character/monster5.atlas");
        monster6Atlas = manager.get("character/monster6.atlas");
        monster7Atlas = manager.get("character/monster7.atlas");
        monster8Atlas = manager.get("character/monster8.atlas");
        monster9Atlas = manager.get("character/monster9.atlas");
        monster10Atlas = manager.get("character/monster10.atlas");
        monster11Atlas = manager.get("character/monster11.atlas");
        monster12Atlas = manager.get("character/monster12.atlas");

        swordStrike = manager.get("sounds/sword-strike.mp3");
        bowFire = manager.get("sounds/bow-fire.mp3");
        hit = manager.get("sounds/hit.mp3");

        arrowTexture = manager.get("arrow.png");

        background = manager.get("white-bg.png");
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error("Assets", "Couldn't load asset '" + asset.fileName + "'", throwable);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
