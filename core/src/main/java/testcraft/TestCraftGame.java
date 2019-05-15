package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import org.mini2Dx.core.game.ScreenBasedGame;

public class TestCraftGame extends ScreenBasedGame {
    public static final String GAME_IDENTIFIER = "testcraft";
    static InputMultiplexer multiplexer = new InputMultiplexer();

    @Override
    public void initialise() {

        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        addScreen(new InGameScreen());
        addScreen(new InGameMenuScreen());
        addScreen(new MainMenuScreen());
        addScreen(new SaveGameScreen());
        addScreen(new LoadGameScreen());
        addScreen(new EquipmentScreen());
        addScreen(new GameOverScreen());

    }

    @Override
    public int getInitialScreenId() {
        return MainMenuScreen.ID;
    }
}




