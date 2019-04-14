package testcraft;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

public class InGameScreen extends BasicGameScreen {
    public static int ID = 1;

	private WorldChunk myFirstChunk;
	
	@Override
    public void initialise(GameContainer gc) {
	    myFirstChunk = new WorldChunk(0f, 0f, new Block[64][64]);
    }
    
    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {
    
    }
    
    @Override
    public void interpolate(GameContainer gc, float alpha) {
    
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
	    myFirstChunk.renderChunk(g);
    }

    @Override
    public int getId(){
	    return ID;
    }
}
