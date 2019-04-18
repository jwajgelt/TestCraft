package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.apache.commons.lang3.ObjectUtils;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.game.*;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.core.screen.transition.NullTransition;

import static testcraft.TestCraftGame.multiplexer;



public class    MenuScreen extends BasicGameScreen {
    public static int ID = 3;

    private static Skin skin;
    public static boolean shouldListen=true;
    private Stage stage;
    private boolean newGameButton=false;
    private  boolean quitButton=false;


    public  boolean addButton (float x, float y, ClickListener clickListener, String text)
    {
        final TextButton button= new TextButton(text,skin,"default");
        button.setHeight(100);
        button.setWidth(200);
        button.setX(x);
        button.setY(y);
        button.addListener(clickListener);
        stage.addActor(button);
        return true;
    }
    public void set()
    {
        if(shouldListen) {
            multiplexer.addProcessor(stage);
            shouldListen = false;
        }
    }


    @Override
    public void initialise(GameContainer gc) {
        try {
            skin = new Skin(Gdx.files.absolute("craftacular/skin/craftacular-ui.json"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void update(GameContainer gc, final ScreenManager screenManager, float delta) {
        set();
        if (!newGameButton)
            newGameButton=addButton(500,400,new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    multiplexer.removeProcessor(stage);
                    screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
                }
            },"RESUME");

        if(!quitButton)
            quitButton=addButton(500,300,new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            },"QUIT");

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            multiplexer.removeProcessor(stage);
            screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
        }

    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
      stage.draw();
     // g.drawStage(stage);// <-doesn't work, draws mirror image of stage. Problem is with mini2x i guess.
    }
    @Override
    public int getId(){
        return ID;
    }
}