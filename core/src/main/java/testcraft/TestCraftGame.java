package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import testcraft.blocks.OneBlockyBoy;

public class TestCraftGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "testcraft";

	private Block myFirstBlock;
	
	@Override
    public void initialise() {
	    myFirstBlock = new OneBlockyBoy();
    }
    
    @Override
    public void update(float delta) {
    
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
		g.drawSprite(myFirstBlock.getBlockSprite(), 0f, 0f);
    }
}
