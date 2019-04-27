package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.geom.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.NullTransition;
import testcraft.blocks.*;
import testcraft.blocks.Void;

import java.awt.*;

import static com.badlogic.gdx.math.MathUtils.floor;


public class InGameScreen extends BasicGameScreen {
    static int ID = 1;

    static final float WIDTH = 1280;
    static final float HEIGHT = 720;

    float SCREEN_WIDTH = 1280;
    float SCREEN_HEIGHT = 720;
    float scale = 1f;
    float transX = 0f;
    float transY = 0f;

    private World world;
    private Player player;
    private float posX, posY;       //testing purposes only

    @Override
    public void initialise(GameContainer gc) {
        world = new World("Default");
        player = world.player;
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;
        world.setPos((int)posX, (int)posY);
    }

    private void playerMovement(float delta){
        float speed=10*delta;
        float left=640-Block.PIXEL_COUNT/2+1;
        float right=640+Block.PIXEL_COUNT/2-1;
        float top=360-Block.PIXEL_COUNT+1;
        float bot=360+Block.PIXEL_COUNT-1;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            float x= floor((left)/(Block.PIXEL_COUNT)+(posX)-speed);
            float y1= floor((top)/(Block.PIXEL_COUNT)+(posY));
            float y2= floor((bot)/(Block.PIXEL_COUNT)+(posY));
            float y3= floor(((top+2*bot)/3)/(Block.PIXEL_COUNT)+(posY));
            float y4= floor(((2*top+bot)/3)/(Block.PIXEL_COUNT)+(posY));
            if(!world.isBlockSolid((int)x, (int)y1) && !world.isBlockSolid((int)x, (int)y2) && !world.isBlockSolid((int)x, (int)y3) && !world.isBlockSolid((int)x, (int)y4))
                player.moveHorizontal(-speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            float x = floor((right)/(Block.PIXEL_COUNT)+(posX)+speed);
            float y1= floor((top)/(Block.PIXEL_COUNT)+(posY));
            float y2= floor((bot)/(Block.PIXEL_COUNT)+(posY));
            float y3= floor(((top+2*bot)/3)/(Block.PIXEL_COUNT)+(posY));
            float y4= floor(((2*top+bot)/3)/(Block.PIXEL_COUNT)+(posY));
            if(!world.isBlockSolid((int)x, (int)y1) && !world.isBlockSolid((int)x, (int)y2) && !world.isBlockSolid((int)x, (int)y3) && !world.isBlockSolid((int)x, (int)y4))
                player.moveHorizontal(speed);
        }
        if(!player.isJumping()){
            float x1=floor((left)/(Block.PIXEL_COUNT)+(posX));
            float x2=floor((right)/(Block.PIXEL_COUNT)+(posX));
            float y=floor((bot)/(Block.PIXEL_COUNT)+(posY)+speed);
            if(!world.isBlockSolid((int)x1, (int)y) && !world.isBlockSolid((int)x2, (int)y))
                player.fall(speed);
            else
                player.groundHim();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isGrounded())
        {
            player.triggerJump();
        }
        if(player.isJumping()){
            float x1=floor((left)/(Block.PIXEL_COUNT)+(posX));
            float x2=floor((right)/(Block.PIXEL_COUNT)+(posX));
            float y=floor((top)/(Block.PIXEL_COUNT)+(posY)+speed*3/2);
            if(!world.isBlockSolid((int)x1, (int)y) && !world.isBlockSolid((int)x2, (int)y))
                player.jump(speed*3/2);
            else
                player.stopJump();
        }
    }

    private void inputHandler (ScreenManager screenManager,float delta)
    {
        playerMovement(delta);//gotta create new class for movement
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            float x= floor((Gdx.input.getX()/scale + transX)/(Block.PIXEL_COUNT)+(posX)); //
            float y= floor((Gdx.input.getY()/scale + transY)/(Block.PIXEL_COUNT)+(posY)); //more elegant
            world.findBlock((int)x,(int)y); //coordinates from pixels to chunks
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screenManager.enterGameScreen(InGameMenuScreen.ID, new NullTransition(), new NullTransition());
            //world.saveToDisk();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) player.setChooseBlock(1);//setting which block you can place
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) player.setChooseBlock(2);//1-coal, 2-cobble, other-dirt
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) player.setChooseBlock(3);//
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)) player.setChooseBlock(4);
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)) player.setChooseBlock(5);
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float x= floor((Gdx.input.getX()/scale + transX)/(Block.PIXEL_COUNT)+(posX)); //
            float y= floor((Gdx.input.getY()/scale + transY)/(Block.PIXEL_COUNT)+(posY)); //more elegant
            world.setBlock((int)x, (int)y, player.getChooseBlock());
        }

    }


    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

        inputHandler( screenManager,delta);
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;
        world.setPos((int)posX, (int)posY);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        SCREEN_HEIGHT = gc.getHeight();
        SCREEN_WIDTH = gc.getWidth();
        float scaleX = SCREEN_WIDTH/WIDTH;
        float scaleY = SCREEN_HEIGHT/HEIGHT;
        scale = Math.min(scaleX, scaleY);
        transX = -Math.max(0, (SCREEN_WIDTH-scale*WIDTH)/2)/scale;
        transY = -Math.max(0, (SCREEN_HEIGHT-scale*HEIGHT)/2)/scale;
        g.drawRect(-1, -1, SCREEN_WIDTH+2, SCREEN_HEIGHT+2);
        g.setScale(scale, scale);
        g.translate(transX, transY);
        g.setClip(0, 0, WIDTH, HEIGHT);
        world.render(g, posX, posY);
        player.renderPlayer(g);
    }

    @Override
    public int getId(){
        return ID;
    }
}