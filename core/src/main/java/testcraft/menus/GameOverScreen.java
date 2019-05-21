package testcraft.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import java.awt.font.ImageGraphicAttribute;

import static testcraft.TestCraftGame.multiplexer;

public class GameOverScreen extends   MenuScreen {

    public static int ID = 7;
    Texture blood;

    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        blood = new Texture(Gdx.files.classpath("blood.png"));
        Image a = new Image(blood);
        a.setSize(WIDTH,HEIGHT);

        System.out.println(a.getHeight()+" "+a.getWidth());

        a.setPosition((WIDTH-a.getWidth())/2,(HEIGHT-a.getHeight())/2);
        stage.addActor(a);


        addButton( 150, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back=true;
            }
        }, "TO MENU");
        addButton(50, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");
        Label label=new Label("GAME OVER", skin, "title");
        label.setX((WIDTH-label.getPrefWidth())/2);
        label.setY(HEIGHT-400);
        stage.addActor(label);


    }

    @Override
    public void goBack (ScreenManager screenManager, GameContainer gc)
    {
        back=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(MainMenuScreen.ID, new NullTransition(), new NullTransition());
    }


    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {
        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);
        if(back)
            goBack(screenManager,gc);
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
