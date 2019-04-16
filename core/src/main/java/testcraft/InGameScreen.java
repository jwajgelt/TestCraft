package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

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

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

	    player.move(delta);
        if(player.posX<360)
        {
            player.posX=360;
            posX+=10*delta;
        }
        if(player.posX>1080)
        {
            player.posX=1080;
            posX-=10*delta;
        }
        if(player.posY<180)
        {
            player.posY=180;
            posY+=10*delta;
        }
        if(player.posY>540)
        {
            player.posY=540;
            posY-=10*delta;
        }
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
