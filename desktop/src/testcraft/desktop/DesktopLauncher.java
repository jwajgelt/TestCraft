package testcraft.desktop;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;
import org.mini2Dx.desktop.DesktopMini2DxConfig;
import testcraft.TestCraftGame;

public class DesktopLauncher {
    public static void main (String[] arg) {

        DesktopMini2DxConfig config = new DesktopMini2DxConfig(TestCraftGame.GAME_IDENTIFIER);
        config.vSyncEnabled = true;
        config.width = 1280;
        config.height = 720;
        DesktopMini2DxGame desktopMini2DxGame = new DesktopMini2DxGame(new TestCraftGame(), config);
    }

}
