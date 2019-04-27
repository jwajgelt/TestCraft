package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.apache.commons.io.FileUtils;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.game.*;
import org.mini2Dx.core.graphics.HeadlessGraphics;
import org.mini2Dx.core.graphics.LibGdxGraphics;
import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.core.screen.transition.NullTransition;

import java.io.File;
import java.io.IOException;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static testcraft.TestCraftGame.multiplexer;


public class    MainMenuScreen extends MenuScreen {
    public static int ID = 2;
    boolean load=false;


    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        addButton(400,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back=true;
            }
        },"NEW GAME");
        addButton(300,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                load=true;
            }
        },"LOAD GAME");
        addButton(200, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");
        Label label=new Label("TESTCR=FT", skin, "title");
        label.setX((WIDTH-label.getPrefWidth())/2);
        label.setY(HEIGHT-200);
        stage.addActor(label);
        System.out.println(label.getPrefHeight()+" "+label.getPrefWidth());
    }
    @Override
    public void goBack (ScreenManager screenManager, GameContainer gc)
    {
        back=false;
        File dest = new File("Default");
        FileUtils.deleteQuietly(dest);
        try {
            FileUtils.forceMkdir(dest);
        }
        catch (IOException e)
        {
           e.printStackTrace();
        }

        multiplexer.removeProcessor(stage);
        screenManager.getGameScreen(InGameScreen.ID).initialise(gc);
        screenManager.enterGameScreen(InGameScreen.ID, new FadeOutTransition(), new FadeInTransition()); //problems with scaling here
    }

    public void goLoad (ScreenManager screenManager)
    {
        load=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(LoadGameScreen.ID, new NullTransition(), new NullTransition());
    }


    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {
        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(back)
                goBack(screenManager,gc);
        if(load)
            goLoad(screenManager);
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