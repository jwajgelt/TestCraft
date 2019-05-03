package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import org.mini2Dx.core.screen.transition.NullTransition;

import static com.badlogic.gdx.math.MathUtils.floor;


public class InGameScreen extends TestCraftScreen {
    static int ID = 1;


    private World world;
    private Player player;
    private PlayerMovementController playerMovementController;

    private float posX, posY;       //testing purposes only

    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);
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
        super.render(gc,g);
        world.render(g, posX, posY);
        player.renderPlayer(g);
    }

    @Override
    public int getId(){
        return ID;
    }

    public Player getPlayer()
    {
        return player;
    }
}