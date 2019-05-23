package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;

public abstract class TestCraftScreen extends BasicGameScreen {


    public static final float WIDTH = 1280; //copy-pasted from InGameScreen
   public static final float HEIGHT = 720;
    public float SCREEN_WIDTH = 1280;
    public  float SCREEN_HEIGHT = 720;
    public float scale = 1f;
    public float transX = 0f;
    public float transY = 0f;
    public  static Skin skin;
    public Stage stage;

    @Override
    public void initialise(GameContainer gc) {
        try {
            skin = new Skin(Gdx.files.classpath("craftacular/skin/craftacular-ui.json"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        stage = (stage==null)? gc.createStage(new ExtendViewport(WIDTH,HEIGHT)): stage;

    }
    @Override
    public void interpolate(GameContainer gc, float alpha) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        float SCREEN_HEIGHT = gc.getHeight();
        float SCREEN_WIDTH = gc.getWidth();
        float scaleX = SCREEN_WIDTH/WIDTH;
        float scaleY = SCREEN_HEIGHT/HEIGHT;
        scale = Math.min(scaleX, scaleY);
        transX = -Math.max(0, (SCREEN_WIDTH-scale*WIDTH)/2)/scale;
        transY = -Math.max(0, (SCREEN_HEIGHT-scale*HEIGHT)/2)/scale;
        g.drawRect(-1, -1, SCREEN_WIDTH+2, SCREEN_HEIGHT+2);
        g.setScale(scale, scale);
        g.translate(transX, transY);
        g.setClip(0, 0, WIDTH, HEIGHT);
        stage.getViewport().update(gc.getWidth(), gc.getHeight(), false);
    }

}
