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



public abstract  class  MenuScreen extends BasicGameScreen {


    protected  static Skin skin;
    protected Stage stage;
    public boolean back=false;
    public Texture bg;
    TextureRegion bgRegion;

    static final float WIDTH = 1280; //copy-pasted from InGameScreen
    static final float HEIGHT = 720;
    float SCREEN_WIDTH = 1280;
    float SCREEN_HEIGHT = 720;
    float scale = 1f;
    float transX = 0f;
    float transY = 0f;

    public  void addButton ( float x,float y,float height, float width, ClickListener clickListener, String text)
    {
        final TextButton button= new TextButton(text,skin,"default");
        button.setHeight(height);
        button.setWidth(width);
        button.setX(x);
        button.setY(y);
        button.addListener(clickListener);
        stage.addActor(button);
    }

    public  void addButton ( float y, ClickListener clickListener, String text)
    {
        addButton(WIDTH/2-100,y,100,200,clickListener,text);
    }




    public void goBack(ScreenManager screenManager)
    {}


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
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgRegion = new TextureRegion(bg);
        bgRegion.setRegion(0, 0, WIDTH, HEIGHT);
    }


    @Override
    abstract public void update(GameContainer gc,  ScreenManager screenManager, float delta) ;


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
        g.translate(transX, transY);      //copy-pasted from InGameScreen

        g.drawTextureRegion(bgRegion,0,0);
        stage.getViewport().update(gc.getWidth(), gc.getHeight(), false);
        g.drawStage(stage);
    }

    @Override
    abstract public int getId();


}