package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Pixmap;
import org.mini2Dx.core.game.ScreenBasedGame;
import testcraft.menus.*;

public class TestCraftGame extends ScreenBasedGame {
    public static final String GAME_IDENTIFIER = "testcraft";
    public static InputMultiplexer multiplexer = new InputMultiplexer();

    @Override
    public void initialise() {
        //setting graphic for cursor
        Pixmap pm=new Pixmap(Gdx.files.classpath("Cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0 ,0 ));


        multiplexer = new InputMultiplexer();
        addScreen(new InGameScreen());
        addScreen(new InGameMenuScreen());
        addScreen(new MainMenuScreen());
        addScreen(new SaveGameScreen());
        addScreen(new LoadGameScreen());
        addScreen(new EquipmentScreen());
        addScreen(new GameOverScreen());
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public int getInitialScreenId() {
        return MainMenuScreen.ID;
    }
}




