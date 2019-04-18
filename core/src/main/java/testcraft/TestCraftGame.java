package testcraft;

import org.mini2Dx.core.game.ScreenBasedGame;

public class TestCraftGame extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "testcraft";

	private WorldChunk myFirstChunk;
	
	@Override
    public void initialise() {
        addScreen(new MenuScreen());
        addScreen(new InGameScreen());
    }

    @Override
    public int getInitialScreenId(){
	    return MenuScreen.ID;
    }
}
