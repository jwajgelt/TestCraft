package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import testcraft.blocks.OneBlockyBoy;

public class TestCraftGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "testcraft";

	private WorldChunk myFirstChunk;
	
	@Override
    public void initialise() {
	    myFirstChunk = new WorldChunk(0f, 0f, new Block[64][64]);
    }
    
    @Override
    public void update(float delta) {
    
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
	    myFirstChunk.renderChunk(g);
    }
}
