package testcraft;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

public class InGameScreen extends BasicGameScreen {
    public static int ID = 1;

    private World world;
    private float posX, posY;       //testing purposes only
	
	@Override
    public void initialise(GameContainer gc) {
	    world = new World("Default", 0, 0);
	    posX = 0f;
	    posY = 0f;
    }
    
    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {
        posX += 30*delta;
        posY += 10*delta;
        world.setPos((int)posX, (int)posY);
    }
    
    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
	    g.drawRect(0, -1, 1281, 721);
        world.render(g, posX, posY);
    }

    @Override
    public int getId(){
	    return ID;
    }
}
