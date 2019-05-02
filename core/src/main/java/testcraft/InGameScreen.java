package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.NullTransition;
import testcraft.blocks.*;
import testcraft.blocks.Void;

import java.awt.*;

import static com.badlogic.gdx.math.MathUtils.floor;


public class InGameScreen extends BasicGameScreen {
    static int ID = 1;

    static final float WIDTH = 1280;
    static final float HEIGHT = 720;

    float SCREEN_WIDTH = 1280;
    float SCREEN_HEIGHT = 720;
    float scale = 1f;
    float transX = 0f;
    float transY = 0f;

    private World world;
    private Player player;
    private PlayerMovementController playerMovementController;

    private float posX, posY;       //testing purposes only

    @Override
    public void initialise(GameContainer gc) {
        world = new World("Default");
        player = world.player;
        playerMovementController=new PlayerMovementController(world, player);
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;
        world.setPos((int)posX, (int)posY);
    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

        playerMovementController.KeyboardInput(delta, posX, posY);
        playerMovementController.MouseInputAndMenus(screenManager, posX, posY, transX, transY, scale);
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;
        world.setPos((int)posX, (int)posY);
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
        g.setClip(0, 0, WIDTH, HEIGHT);
        world.render(g, posX, posY);
        player.renderPlayer(g);
    }

    @Override
    public int getId(){
        return ID;
    }
}