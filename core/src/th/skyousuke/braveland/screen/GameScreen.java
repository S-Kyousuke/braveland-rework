package th.skyousuke.braveland.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import th.skyousuke.braveland.ViewDirection;
import th.skyousuke.braveland.WorldController;
import th.skyousuke.braveland.WorldRenderer;
import th.skyousuke.braveland.object.AbstractCharacter;
import th.skyousuke.braveland.object.npc.monster.Monster;
import th.skyousuke.braveland.object.npc.monster.monster1.Monster1;
import th.skyousuke.braveland.object.npc.monster.monster10.Monster10;
import th.skyousuke.braveland.object.npc.monster.monster11.Monster11;
import th.skyousuke.braveland.object.npc.monster.monster12.Monster12;
import th.skyousuke.braveland.object.npc.monster.monster2.Monster2;
import th.skyousuke.braveland.object.npc.monster.monster3.Monster3;
import th.skyousuke.braveland.object.npc.monster.monster4.Monster4;
import th.skyousuke.braveland.object.npc.monster.monster5.Monster5;
import th.skyousuke.braveland.object.npc.monster.monster6.Monster6;
import th.skyousuke.braveland.object.npc.monster.monster7.Monster7;
import th.skyousuke.braveland.object.npc.monster.monster8.Monster8;
import th.skyousuke.braveland.object.npc.monster.monster9.Monster9;
import th.skyousuke.braveland.object.npc.partner.Partner;
import th.skyousuke.braveland.object.npc.partner.partner1.Partner1;
import th.skyousuke.braveland.object.npc.partner.partner2.Partner2;
import th.skyousuke.braveland.object.npc.partner.partner3.Partner3;
import th.skyousuke.braveland.object.npc.partner.partner4.Partner4;
import th.skyousuke.braveland.object.npc.partner.partner5.Partner5;
import th.skyousuke.braveland.object.player.Player;
import th.skyousuke.braveland.utils.Assets;
import th.skyousuke.braveland.utils.CameraHelper;

public class GameScreen extends AbstractScreen {

    private static final int MIN_FPS = 10;

    private WorldController worldController = new WorldController();
    private WorldRenderer worldRenderer = new WorldRenderer(worldController);
    private Stage stage;
    private SelectBox<String> addObjectSelectBox;
    private TextButton addObjectButton;
    private TextButton zoomInButton;
    private TextButton zoomOutButton;
    private TextButton resetZoomButton;
    private Label cameraSpeedText;
    private Slider cameraSpeedSlider;

    private TextButton attackButton;
    private TextButton specialAttackButton;
    private TextButton specialAttack2Button;
    private TextButton skillButton;
    private TextButton walkLeftButton;
    private TextButton walkRightButton;
    private TextButton jumpButton;

    private List<AbstractCharacter> characterList;
    private ScrollPane characterListScrollPane;

    private AbstractCharacter selectedCharacter;

    public GameScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(960, 540));

        addObjectButton = new TextButton("Add", Assets.instance.skin);
        addObjectButton.setPosition(0, 0);
        addObjectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbstractCharacter characterToAdd = null;
                switch (addObjectSelectBox.getSelected()) {
                    case "monster1":
                        characterToAdd = new Monster1();
                        break;
                    case "monster2":
                        characterToAdd = new Monster2();
                        break;
                    case "monster3":
                        characterToAdd = new Monster3();
                        break;
                    case "monster4":
                        characterToAdd = new Monster4();
                        break;
                    case "monster5":
                        characterToAdd = new Monster5();
                        break;
                    case "monster6":
                        characterToAdd = new Monster6();
                        break;
                    case "monster7":
                        characterToAdd = new Monster7();
                        break;
                    case "monster8":
                        characterToAdd = new Monster8();
                        break;
                    case "monster9":
                        characterToAdd = new Monster9();
                        break;
                    case "monster10":
                        characterToAdd = new Monster10();
                        break;
                    case "monster11":
                        characterToAdd = new Monster11();
                        break;
                    case "monster12":
                        characterToAdd = new Monster12();
                        break;
                    case "partner1":
                        characterToAdd = new Partner1();
                        break;
                    case "partner2":
                        characterToAdd = new Partner2();
                        break;
                    case "partner3":
                        characterToAdd = new Partner3();
                        break;
                    case "partner4":
                        characterToAdd = new Partner4();
                        break;
                    case "partner5":
                        characterToAdd = new Partner5();
                        break;
                    default:
                }
                if (characterToAdd != null)
                    worldController.getLevel().addCharacter(characterToAdd, 500, 500);
            }
        });
        addObjectSelectBox = new SelectBox<>(Assets.instance.skin);
        Array<String> items = new Array<>();
        items.add("monster1");
        items.add("monster2");
        items.add("monster3");
        items.add("monster4");
        items.add("monster5");
        items.add("monster6");
        items.add("monster7");
        items.add("monster8");
        items.add("monster9");
        items.add("monster10");
        items.add("monster11");
        items.add("monster12");
        items.add("partner1");
        items.add("partner2");
        items.add("partner3");
        items.add("partner4");
        items.add("partner5");
        addObjectSelectBox.setItems(items);
        addObjectSelectBox.pack();
        addObjectSelectBox.setPosition(addObjectButton.getX() + addObjectButton.getWidth(), 0);

        zoomInButton = new TextButton("Zoom In", Assets.instance.skin);
        zoomInButton.setPosition(addObjectSelectBox.getX() + addObjectSelectBox.getWidth(), 0);
        zoomInButton.addListener(new ClickListener() {
            boolean stopZoom;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stopZoom = false;
                zoomOutButton.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        worldController.getCameraHelper().addZoom(-1f * Gdx.graphics.getDeltaTime());;
                        return stopZoom;
                    }
                });
                return super.touchDown(event, x, y, pointer, button);
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stopZoom = true;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        zoomOutButton = new TextButton("Zoom Out", Assets.instance.skin);
        zoomOutButton.setPosition(zoomInButton.getX() + zoomInButton.getWidth(), 0);
        zoomOutButton.addListener(new ClickListener() {
            boolean stopZoom;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stopZoom = false;
                zoomOutButton.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        worldController.getCameraHelper().addZoom(1f * Gdx.graphics.getDeltaTime());;
                        return stopZoom;
                    }
                });
                return super.touchDown(event, x, y, pointer, button);
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stopZoom = true;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        resetZoomButton = new TextButton("Reset Zoom", Assets.instance.skin);
        resetZoomButton.setPosition(zoomOutButton.getX() + zoomOutButton.getWidth(), 0);
        resetZoomButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                worldController.getCameraHelper().setZoom(1f);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        cameraSpeedText = new Label("Camera Speed: ", Assets.instance.skin);
        cameraSpeedText.setPosition(resetZoomButton.getX() + resetZoomButton.getWidth(), 0);

        cameraSpeedSlider = new Slider(CameraHelper.MIN_SPEED, 0.1f, 0.005f, false, Assets.instance.skin);
        cameraSpeedSlider.setValue(0.1f);
        cameraSpeedSlider.setWidth(100);
        cameraSpeedSlider.setPosition(cameraSpeedText.getX() + cameraSpeedText.getWidth(), 0);
        cameraSpeedSlider.addListener(new ClickListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                worldController.getCameraHelper().setSpeed(cameraSpeedSlider.getValue());
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                worldController.getCameraHelper().setSpeed(cameraSpeedSlider.getValue());
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                worldController.getCameraHelper().setSpeed(cameraSpeedSlider.getValue());
                super.touchUp(event, x, y, pointer, button);
            }
        });

        attackButton = new TextButton("Atk", Assets.instance.skin);
        attackButton.setPosition(cameraSpeedSlider.getX() + cameraSpeedSlider.getWidth() + 20, 0);
        attackButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selectedCharacter instanceof Player) {
                    ((Player) selectedCharacter).attack();
                } else if (selectedCharacter instanceof Partner) {
                    ((Partner) selectedCharacter).attack();
                } else if (selectedCharacter instanceof Monster) {
                    ((Monster) selectedCharacter).attack();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        specialAttackButton = new TextButton("S.Atk", Assets.instance.skin);
        specialAttackButton.setPosition(attackButton.getX() + attackButton.getWidth(), 0);
        specialAttackButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selectedCharacter instanceof Player) {
                    ((Player) selectedCharacter).specialAttack();
                } else if (selectedCharacter instanceof Partner) {
                    ((Partner) selectedCharacter).specialAttack();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        specialAttack2Button = new TextButton("S.Atk 2", Assets.instance.skin);
        specialAttack2Button.setPosition(specialAttackButton.getX() + specialAttackButton.getWidth(), 0);
        specialAttack2Button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selectedCharacter instanceof Partner4) {
                    ((Partner4) selectedCharacter).specialAttack2();
                } else if (selectedCharacter instanceof Partner5) {
                    ((Partner5) selectedCharacter).specialAttack2();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        skillButton = new TextButton("Skill", Assets.instance.skin);
        skillButton.setPosition(specialAttack2Button.getX() + specialAttack2Button.getWidth(), 0);
        skillButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (selectedCharacter instanceof Partner5) {
                    ((Partner5) selectedCharacter).heal();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        walkLeftButton = new TextButton("Walk Left", Assets.instance.skin);
        walkLeftButton.setPosition(cameraSpeedSlider.getX() + cameraSpeedSlider.getWidth() + 20, addObjectButton.getHeight());
        walkLeftButton.addListener(new ClickListener() {
            boolean stopMove;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stopMove = false;
                zoomOutButton.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        selectedCharacter.move(ViewDirection.LEFT);
                        return stopMove;
                    }
                });
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stopMove = true;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        walkRightButton = new TextButton("Walk Right", Assets.instance.skin);
        walkRightButton.setPosition(walkLeftButton.getX() + walkLeftButton.getWidth(), addObjectButton.getHeight());
        walkRightButton.addListener(new ClickListener() {
            boolean stopMove;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stopMove = false;
                zoomOutButton.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        selectedCharacter.move(ViewDirection.RIGHT);
                        return stopMove;
                    }
                });
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stopMove = true;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        jumpButton = new TextButton("Jump", Assets.instance.skin);
        jumpButton.setPosition(cameraSpeedSlider.getX() + cameraSpeedSlider.getWidth() + 20, addObjectButton.getHeight() * 2);
        jumpButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectedCharacter.jump();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        characterList = new List<>(Assets.instance.skin);
        characterList.setItems(worldController.getLevel().getCharacters());
        characterList.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectedCharacter = characterList.getSelected();
                worldController.getCameraHelper().setTarget(selectedCharacter);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        characterListScrollPane = new ScrollPane(characterList, Assets.instance.skin);
        characterListScrollPane.setWidth(150);
        characterListScrollPane.setHeight(100);
        characterListScrollPane.setPosition(960 - characterListScrollPane.getWidth(), 0);
        characterListScrollPane.setScrollingDisabled(true, false);
        characterListScrollPane.setFadeScrollBars(false);

        stage.addActor(addObjectSelectBox);
        stage.addActor(addObjectButton);
        stage.addActor(zoomInButton);
        stage.addActor(zoomOutButton);
        stage.addActor(resetZoomButton);
        stage.addActor(attackButton);
        stage.addActor(specialAttackButton);
        stage.addActor(specialAttack2Button);
        stage.addActor(skillButton);
        stage.addActor(walkLeftButton);
        stage.addActor(walkRightButton);
        stage.addActor(jumpButton);
        stage.addActor(cameraSpeedText);
        stage.addActor(cameraSpeedSlider);
        stage.addActor(characterListScrollPane);

        Gdx.input.setInputProcessor(stage);

        selectedCharacter = worldController.getPlayer();
    }

    public GameScreen(Game game, boolean skipIntro) {
        this(game);
        if (skipIntro) {
            worldController.skipIntro();
        }
    }

    @Override
    public void render(float delta) {
        float adjustedDelta = Math.min(delta, 1f / MIN_FPS);

        characterList.setItems(worldController.getLevel().getCharacters());
        // TODO
        if (selectedCharacter == null) {
            selectedCharacter = characterList.getSelected();
        }


        if (!pause) {
            GdxAI.getTimepiece().update(adjustedDelta);
            worldController.update(adjustedDelta);
            worldRenderer.render();
        } else if (ready) {
            pause = false;
        }
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void hide() {
        stage.dispose();
    }
}
