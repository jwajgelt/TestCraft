package testcraft.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.SnapshotArray;
import org.lwjgl.Sys;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import testcraft.TestCraftGame;

import java.lang.reflect.Field;

public class DesktopLauncher {
    public static void main (String[] arg) throws Exception {


        DesktopMini2DxConfig config = new DesktopMini2DxConfig(TestCraftGame.GAME_IDENTIFIER);
        config.pauseWhenMinimized=false;
        config.pauseWhenBackground=false;
        config.vSyncEnabled = true;
        config.width = 1280;
        config.height = 720;
        config.useGL30=true; //jak cos to mozna wylaczyc
        DesktopMini2DxGame desktopMini2DxGame = new DesktopMini2DxGame(new TestCraftGame(), config);
    }

}
