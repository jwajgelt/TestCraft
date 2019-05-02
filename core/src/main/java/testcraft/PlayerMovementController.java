package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static com.badlogic.gdx.math.MathUtils.floor;
import static testcraft.InGameScreen.WIDTH;
import static testcraft.InGameScreen.HEIGHT;

public class PlayerMovementController {
    private World world;
    private Player player;

    PlayerMovementController(World world, Player player){
        this.world=world;
        this.player=player;
    }

    private float left=640-Block.PIXEL_COUNT/2+1;
    private float right=640+Block.PIXEL_COUNT/2-1;
    private float top=360-Block.PIXEL_COUNT-1;
    private float bot=360+Block.PIXEL_COUNT+1;

    private void moveHorizontal(float delta, float posX, float posY){
        float y1= floor((top)/(Block.PIXEL_COUNT)+(posY));//top to bot
        float y2= floor((bot)/(Block.PIXEL_COUNT)+(posY));
        float y3= floor(((top+2*bot)/3)/(Block.PIXEL_COUNT)+(posY));
        float y4= floor(((2*top+bot)/3)/(Block.PIXEL_COUNT)+(posY));
        float x1= floor((left)/(Block.PIXEL_COUNT)+(posX)+delta*player.getHorizontalSpeed());//left to right
        float x2= floor((right)/(Block.PIXEL_COUNT)+(posX)+delta*player.getHorizontalSpeed());
        if(!world.isBlockSolid((int)x1, (int)y1) && !world.isBlockSolid((int)x1, (int)y2) && !world.isBlockSolid((int)x1, (int)y3) && !world.isBlockSolid((int)x1, (int)y4)
            && !world.isBlockSolid((int)x2, (int)y1) && !world.isBlockSolid((int)x2, (int)y2) && !world.isBlockSolid((int)x2, (int)y3) && !world.isBlockSolid((int)x2, (int)y4)){
            player.moveHorizontal(delta*player.getHorizontalSpeed());
        }else{
            player.stopHorizontal(3);
        }
        player.stopHorizontal(1);
    }

    private void moveVertical(float delta, float posX, float posY){
        float x1=floor((left)/(Block.PIXEL_COUNT)+(posX));//left to right
        float x2=floor((right)/(Block.PIXEL_COUNT)+(posX));
        float y1=floor((bot)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);//bot to top
        float y2=floor((top)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);
        if(!world.isBlockSolid((int)x1, (int)y1) && !world.isBlockSolid((int)x2, (int)y1) && !world.isBlockSolid((int)x1, (int)y2) && !world.isBlockSolid((int)x2, (int)y2))
            player.moveVertical(player.getVerticalSpeed()*delta);
        else{
            if(world.isBlockSolid((int)x1, (int)y1) || world.isBlockSolid((int)x2, (int)y1) )
                player.groundHim();
            player.stopVertical(10);
        }

    }

    void KeyboardInput(float delta, float posX, float posY){

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isGrounded())
        {
            player.triggerJump();
        }
        player.decreaseVerticalSpeed();
        moveVertical(delta, posX, posY);

        //move horizontal
        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            player.decreaseHorizontalSpeed();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            player.increaseHorizontalSpeed();
        }
        moveHorizontal(delta, posX, posY);

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
