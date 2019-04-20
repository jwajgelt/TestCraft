package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.mini2Dx.core.game.ScreenBasedGame;
import org.mini2Dx.core.graphics.Graphics;
import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.graphics.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.mini2Dx.core.game.GameWrapper;
import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.geom.Shape;
import org.mini2Dx.core.graphics.LibGdxGraphics;
import org.mini2Dx.core.graphics.ShapeTextureCache;
import org.mini2Dx.core.graphics.TextureRegion;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestCraftGame extends ScreenBasedGame {
    public static final String GAME_IDENTIFIER = "testcraft";
    public static InputMultiplexer multiplexer = new InputMultiplexer();
    private WorldChunk myFirstChunk;

    @Override
    public void initialise() {


        System.out.println(graphics);
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        addScreen(new MenuScreen());
        addScreen(new InGameScreen());
        addScreen(new MainMenuScreen());

        ////////////////////////////////////////////////////////////////// LITTLE CHEATING HERE:
        Class<LibGdxGraphics> cl = LibGdxGraphics.class;
            try {
                Field fi = cl.getDeclaredField("spriteBatch");
                fi.setAccessible(true);
                SpriteBatch spriteBatch= (SpriteBatch)fi.get(graphics);


                fi = cl.getDeclaredField("polygonSpriteBatch");
                fi.setAccessible(true);
                PolygonSpriteBatch polygonSpriteBatch= (PolygonSpriteBatch)fi.get(graphics);

                fi = cl.getDeclaredField("shapeRenderer");
                fi.setAccessible(true);
                ShapeRenderer shapeRenderer= (ShapeRenderer)fi.get(graphics);

                fi = cl.getDeclaredField("gameWrapper");
                fi.setAccessible(true);
                GameWrapper gameWrapper= ( GameWrapper)fi.get(graphics);
                graphics= new MyGraphics(gameWrapper,spriteBatch,polygonSpriteBatch,shapeRenderer);

            } catch (Exception e) {
                System.out.println("Failed: " + e);
            }
        System.out.println(graphics);
            ///////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public int getInitialScreenId() {
        return MainMenuScreen.ID;
    }
}




