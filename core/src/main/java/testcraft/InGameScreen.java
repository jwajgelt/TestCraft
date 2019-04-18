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
            float x= (Gdx.input.getX()/(Block.PIXEL_COUNT)+(posX));//here and
            float y= (Gdx.input.getY()/(Block.PIXEL_COUNT)+(posY));//here should be floor
            if(x<0)
                x-=(Block.PIXEL_COUNT-1)/Block.PIXEL_COUNT;
            if(y<0)
                y-=(Block.PIXEL_COUNT-1)/Block.PIXEL_COUNT;//but ifs are ok
            world.findBlock((int)x,(int)y); //coordinates from pixels to chunks
        }

    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

        inputHandler(delta);
        posX=player.getX() - 640/Block.PIXEL_COUNT;
        posY=player.getY() - 360/Block.PIXEL_COUNT;
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
