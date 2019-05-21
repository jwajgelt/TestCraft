package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import testcraft.menus.GameOverScreen;


public class InGameScreen extends TestCraftScreen {
    public static int ID = 1;


    private World world;
    private Player player;
    private PlayerMovementController playerMovementController;
    private  ProgressBar healthBar;

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
        addHealthBar();

    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

        //player updates
        healthBar.setValue(player.getHp().getHealthPoints());

        playerMovementController.KeyboardInput(delta, posX, posY);
        playerMovementController.MouseInputAndMenus(screenManager, posX, posY, transX, transY, scale,delta);
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;

        //world updates
        world.setPos((int)posX, (int)posY);
        world.update(delta);

        //check for game over screen
        if(player.getHp().isDead())
           screenManager.enterGameScreen(GameOverScreen.ID, new NullTransition(), new NullTransition());

    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc,g);
        world.render(g, posX, posY);
       player.renderPlayer(g);
        g.drawStage(stage);

    }

    @Override
    public int getId(){
        return ID;
    }

    public Player getPlayer() {
        return player;
    }

    private void addHealthBar () {
        ProgressBar.ProgressBarStyle style = skin.get("health", ProgressBar.ProgressBarStyle.class);
        skin.getTiledDrawable("heart-bg").setMinWidth(0.0f);
        style.background = skin.getTiledDrawable("heart-bg");
        skin.getTiledDrawable("heart").setMinWidth(0.0f);
        style.knobBefore = skin.getTiledDrawable("heart");
        healthBar = new ProgressBar(0.0f, Player.PLAYER_HP, .1f, false, skin, "health");
        healthBar.setSize(180, 18);
        healthBar.setPosition(100, 100);
        stage.addActor(healthBar);
    }
}