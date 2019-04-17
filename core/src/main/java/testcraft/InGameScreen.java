package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import testcraft.blocks.*;
import testcraft.blocks.Void;

import java.awt.*;

public class InGameScreen extends BasicGameScreen {
    public static int ID = 1;

    private World world;
    private Player player;
    private float posX, posY;       //testing purposes only
	
	@Override
    public void initialise(GameContainer gc) {
	    world = new World("Default", 0, 0);
	    player = new Player(640, 360);
	    posX = 0f;
	    posY = 0f;
    }

    private void inputHandler (float delta)
    {
        player.move(delta); //player move
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int x=Gdx.input.getX();
            int y=Gdx.input.getY();
            world.findBlock((int)(x/(Block.PIXEL_COUNT)+(posX)),(int)(y/(Block.PIXEL_COUNT)+(posY))); //coordinates from pixels to chunks
        }

    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

        inputHandler(delta);
        posX=player.getX() - 640;
        posY=player.getY() - 360;
        world.setPos((int)posX, (int)posY);
    }
    
    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
	    g.drawRect(0, -1, 1281, 721);
        world.render(g, posX, posY);
        player.renderPlayer(g);
    }

    @Override
    public int getId(){
	    return ID;
    }
}
