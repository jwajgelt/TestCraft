package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import org.mini2Dx.core.game.ScreenBasedGame;

public class TestCraftGame extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "testcraft";
    public static InputMultiplexer multiplexer= new InputMultiplexer();
	private WorldChunk myFirstChunk;
	
	@Override
    public void initialise() {
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        addScreen(new MenuScreen());
        addScreen(new InGameScreen());
        addScreen(new MainMenuScreen());

    }

    @Override
    public int getInitialScreenId(){
	    return MainMenuScreen.ID;
    }
}
