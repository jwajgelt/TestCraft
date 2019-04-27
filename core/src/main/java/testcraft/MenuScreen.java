package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.game.*;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static testcraft.TestCraftGame.multiplexer;



public class    MenuScreen extends BasicGameScreen {
    public static int ID = 3;

    private static Skin skin;
    private Stage stage;
    private  Stage background;
    public   boolean startGame=false;
    public boolean toMainMenu=false;


    public  void addButton (float x, float y, ClickListener clickListener, String text)
    {
        final TextButton button= new TextButton(text,skin,"default");
        button.setHeight(100);
        button.setWidth(200);
        button.setX(x);
        button.setY(y);
        button.addListener(clickListener);
        stage.addActor(button);
    }


    @Override
    public void initialise(GameContainer gc) {
        try {
            skin = new Skin(Gdx.files.absolute("craftacular/skin/craftacular-ui.json"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        stage = gc.createStage(new ScreenViewport());

        addButton(500,400,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame=true;
            }
        },"RESUME");

        addButton(500,300,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toMainMenu=true;
            }
        },"TO MENU");



        addButton(500, 200, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");

    }



    public void toGame (ScreenManager screenManager)
    {
        startGame=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
    }
    public void M (ScreenManager screenManager)
    {
        toMainMenu=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(MainMenuScreen.ID, new NullTransition(), new NullTransition());

    }

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {
        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);
        stage.getViewport().update(width, height, true);

        if(startGame || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            toGame(screenManager);
        if(toMainMenu)
            M(screenManager);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawStage(stage);
    }
    @Override
    public int getId(){
        return ID;
    }
}