package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static testcraft.TestCraftGame.multiplexer;



public class   InGameMenuScreen extends MenuScreen {

    public static int ID = 3;
    private  boolean toMainMenu=false;
    private  boolean save=false;


    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        addButton(400,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back=true;
            }
        },"RESUME");
        addButton( 300, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save=true;
            }
        }, "SAVE");
        addButton(200,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toMainMenu=true;
            }
        },"TO MENU");
        addButton( 100, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");
    }


    @Override
    public void goBack (ScreenManager screenManager, GameContainer gameContainer)
    {
        back=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
    }

    public void goToMainMenu(ScreenManager screenManager)
    {
        toMainMenu=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(MainMenuScreen.ID, new NullTransition(), new NullTransition());
    }
    void goSave(ScreenManager screenManager)
    {
        save=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(SaveGameScreen.ID, new NullTransition(), new NullTransition());
    }

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {

        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(back || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            goBack(screenManager,gc);
        if(toMainMenu)
            goToMainMenu(screenManager);
        if(save)
            goSave(screenManager);

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc,g);
    }
    @Override
    public int getId(){
        return ID;
    }
}