package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static com.badlogic.gdx.math.MathUtils.floor;

public class PlayerMovementController {
    private World world;
    private Player player;

    PlayerMovementController(World world, Player player){
        this.world=world;
        this.player=player;
    }

    void KeyboardInput(float delta, float posX, float posY){
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
            float y=floor((top)/(Block.PIXEL_COUNT)+(posY)+speed);
            if(!world.isBlockSolid((int)x1, (int)y) && !world.isBlockSolid((int)x2, (int)y))
                player.jump(speed);
            else
                player.stopJump();
        }
    }

    void MouseInputAndMenus(ScreenManager screenManager, float posX, float posY, float transX, float transY, float scale){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            float x= floor((Gdx.input.getX()/scale + transX)/(Block.PIXEL_COUNT)+(posX)); //
            float y= floor((Gdx.input.getY()/scale + transY)/(Block.PIXEL_COUNT)+(posY)); //more elegant
            world.findBlock((int)x,(int)y); //coordinates from pixels to chunks
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screenManager.enterGameScreen(InGameMenuScreen.ID, new NullTransition(), new NullTransition());
            world.saveToDisk();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)) player.setChooseBlock(1);//setting which block you can place
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) player.setChooseBlock(2);//1-coal, 2-cobble, 3-wood, other-dirt
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)) player.setChooseBlock(3);//
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)) player.setChooseBlock(4);
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)) player.setChooseBlock(5);
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float x= floor((Gdx.input.getX()/scale + transX)/(Block.PIXEL_COUNT)+(posX)); //
            float y= floor((Gdx.input.getY()/scale + transY)/(Block.PIXEL_COUNT)+(posY)); //more elegant
            world.setBlock((int)x, (int)y, player.getChooseBlock());
        }
    }

}
