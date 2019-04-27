package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.game.*;
import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static testcraft.TestCraftGame.multiplexer;



public class   InGameMenuScreen extends MenuScreen {

    public static int ID = 3;
    private  boolean toMainMenu=false;


    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        addButton(400,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back=true;
            }
        },"RESUME");
        addButton(300,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toMainMenu=true;
            }
        },"TO MENU");
        addButton( 200, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");
    }


    @Override
    public void goBack (ScreenManager screenManager)
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

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {

        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(back || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            goBack(screenManager);
        if(toMainMenu)
            goToMainMenu(screenManager);

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