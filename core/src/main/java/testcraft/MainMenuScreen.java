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
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.game.*;
import org.mini2Dx.core.graphics.HeadlessGraphics;
import org.mini2Dx.core.graphics.LibGdxGraphics;
import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;
import static testcraft.TestCraftGame.multiplexer;


public class    MainMenuScreen extends BasicGameScreen {
    public static int ID = 2;

    private static Skin skin;
    private Stage stage;
    public   boolean startGame=false;
    public Texture bg;
    TextureRegion bgRegion;

    static final float WIDTH = 1280;
    static final float HEIGHT = 720;

    float SCREEN_WIDTH = 1280;
    float SCREEN_HEIGHT = 720;
    float scale = 1f;
    float transX = 0f;
    float transY = 0f;




    public  void addButton (float x, float y, ClickListener clickListener, String text)
    {
        TextButton button= new TextButton(text,skin,"default");
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

        stage = gc.createStage(new ExtendViewport(WIDTH,HEIGHT));

        bg = new Texture(Gdx.files.absolute("craftacular/raw/dirt.png"));
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat); // <- it will be background in the future


        bgRegion = new TextureRegion(bg);
        bgRegion.setRegion(0, 0, 1280, 720);



        addButton(500,400,new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startGame=true;
            }
        },"NEW GAME");

        addButton(500, 300, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, "QUIT");


    }
    public void toGame (ScreenManager screenManager, GameContainer gc)
    {
        startGame=false;
        multiplexer.removeProcessor(stage);
        screenManager.getGameScreen(InGameScreen.ID).initialise(gc);
        screenManager.enterGameScreen(InGameScreen.ID, new FadeOutTransition(), new FadeInTransition());
    }

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {
        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(startGame)
                toGame(screenManager,gc);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {

        SCREEN_HEIGHT = gc.getHeight();
        SCREEN_WIDTH = gc.getWidth();
        float scaleX = SCREEN_WIDTH/WIDTH;
        float scaleY = SCREEN_HEIGHT/HEIGHT;
        scale = Math.min(scaleX, scaleY);
        transX = -Math.max(0, (SCREEN_WIDTH-scale*WIDTH)/2)/scale;
        transY = -Math.max(0, (SCREEN_HEIGHT-scale*HEIGHT)/2)/scale;
        g.drawRect(-1, -1, SCREEN_WIDTH+2, SCREEN_HEIGHT+2);
        g.setScale(scale, scale);
        g.translate(transX, transY);
        g.drawTextureRegion(bgRegion,0,0);//,WIDTH*scale,HEIGHT*scale);
        stage.getViewport().update(gc.getWidth(), gc.getHeight(), false);
        g.drawStage(stage);

    }
    @Override
    public int getId(){
        return ID;
    }
}