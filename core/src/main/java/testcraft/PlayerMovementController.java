package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import testcraft.blocks.Destroyable;
import testcraft.blocks.Harvestable;
import testcraft.blocks.Void;
import testcraft.items.LazrPickaxe;
import testcraft.menus.GameOverScreen;
import testcraft.menus.InGameMenuScreen;

import static com.badlogic.gdx.math.MathUtils.floor;
import static testcraft.InGameScreen.HEIGHT;
import static testcraft.InGameScreen.WIDTH;

public class PlayerMovementController {
    private World world;
    private Player player;
    private Sound miningSound = Gdx.audio.newSound(Gdx.files.classpath("pickaxe.ogg")); //tak sie wczytuje
    private Sound catSound = Gdx.audio.newSound(Gdx.files.classpath("cat.wav")); //tak sie wczytuje
    private  float remainingMiningTime=0;

    PlayerMovementController(World world, Player player){
        this.world=world;
        this.player=player;
        miningSound.loop();
        miningSound.pause();
    }
    private float fallStart=360-Block.PIXEL_COUNT;
    private float left=640-Block.PIXEL_COUNT/2+1-0.01f;
    private float right=640+Block.PIXEL_COUNT/2-1+0.01f;
    private float top=360-Block.PIXEL_COUNT+1-0.01f;
    private float bot=360+Block.PIXEL_COUNT-1+0.01f;



    private boolean checkCollsions ( float y1, float y2,float y3, float y4, float x1, float x2)
    {
        return (!world.isBlockSolid((int)x1, (int)y1) && !world.isBlockSolid((int)x1, (int)y2) && !world.isBlockSolid((int)x1, (int)y3) && !world.isBlockSolid((int)x1, (int)y4)
                && !world.isBlockSolid((int)x2, (int)y1) && !world.isBlockSolid((int)x2, (int)y2) && !world.isBlockSolid((int)x2, (int)y3) && !world.isBlockSolid((int)x2, (int)y4));
    }


    private void moveHorizontal(float delta, float posX, float posY){
        player.stopHorizontal(1);
        float y1= floor((top)/(Block.PIXEL_COUNT)+(posY));//top to bot
        float y2= floor((bot)/(Block.PIXEL_COUNT)+(posY));
        float y3= floor(((top+2*bot)/3)/(Block.PIXEL_COUNT)+(posY));
        float y4= floor(((2*top+bot)/3)/(Block.PIXEL_COUNT)+(posY));
        float x1= floor((left)/(Block.PIXEL_COUNT)+(posX)+delta*player.getHorizontalSpeed());//left to right
        float x2= floor((right)/(Block.PIXEL_COUNT)+(posX)+delta*player.getHorizontalSpeed());
        //if(!world.isBlockSolid((int)x1, (int)y1) && !world.isBlockSolid((int)x1, (int)y2) && !world.isBlockSolid((int)x1, (int)y3) && !world.isBlockSolid((int)x1, (int)y4)
             //   && !world.isBlockSolid((int)x2, (int)y1) && !world.isBlockSolid((int)x2, (int)y2) && !world.isBlockSolid((int)x2, (int)y3) && !world.isBlockSolid((int)x2, (int)y4)) {WAS
        if(checkCollsions(y1,y2,y3,y4,x1,x2)){ //IS
            player.moveHorizontal(delta*player.getHorizontalSpeed());
        }else{
            player.stopHorizontal(2);
        }

    }

    private void moveVertical(float delta, float posX, float posY){
        float y1=floor((bot)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);//bot to top
        float y2=floor((top)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);
        float y3= floor(((top+2*bot)/3)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);
        float y4= floor(((2*top+bot)/3)/(Block.PIXEL_COUNT)+(posY)+player.getVerticalSpeed()*delta);
        float x1=floor((left)/(Block.PIXEL_COUNT)+(posX));//left to right
        float x2=floor((right)/(Block.PIXEL_COUNT)+(posX));
        //if(!world.isBlockSolid((int)x1, (int)y1) && !world.isBlockSolid((int)x1, (int)y2) && !world.isBlockSolid((int)x1, (int)y3) && !world.isBlockSolid((int)x1, (int)y4)
               // && !world.isBlockSolid((int)x2, (int)y1) && !world.isBlockSolid((int)x2, (int)y2) && !world.isBlockSolid((int)x2, (int)y3) && !world.isBlockSolid((int)x2, (int)y4)) { WAS
            if(checkCollsions(y1,y2,y3,y4,x1,x2)) { //IS
            player.moveVertical(player.getVerticalSpeed() * delta);
            player.decreaseVerticalSpeed();
        }else{
            player.stopVertical(12);
            if(world.isBlockSolid((int)x1, (int)y1) || world.isBlockSolid((int)x2, (int)y1) ) {
                player.groundHim();
            }
        }
        if(player.isVerticalSpeedCloseZero()){
            player.lowerHp(player.getY() - HEIGHT/2/Block.PIXEL_COUNT - fallStart);
            fallStart=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;
        }
    }

    void KeyboardInput(float delta, float posX, float posY){

        //move horizontal
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.T) && Gdx.input.isKeyJustPressed(Input.Keys.C)) //easter egg
            catSound.play();
            else
            player.decreaseHorizontalSpeed();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.increaseHorizontalSpeed();
        }
        moveHorizontal(delta, posX, posY);

        posX=player.getX() - WIDTH/2/Block.PIXEL_COUNT;
        posY=player.getY() - HEIGHT/2/Block.PIXEL_COUNT;

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isGrounded())
        {
            player.triggerJump();
        }
        player.decreaseVerticalSpeed();
        moveVertical(delta, posX, posY);

    }

    void MouseInputAndMenus(ScreenManager screenManager, float posX, float posY, float transX, float transY, float scale, float delta) {

        soundHandler(delta);


        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int x = floor((Gdx.input.getX() / scale + transX) / (Block.PIXEL_COUNT) + (posX)); //
            int y = floor((Gdx.input.getY() / scale + transY) / (Block.PIXEL_COUNT) + (posY)); //more elegant

            if (player.isReachable(world.getRectangle(x, y), world.isBlockSolid(x, y)) || Gdx.input.isKeyPressed(Input.Keys.G)) {
                Block block = world.findBlock(x, y);
                if (block instanceof Destroyable) {
                    ((Destroyable) block).changeDurability(-delta, player.getEquipment().getItem());
                    remainingMiningTime = 0.12f; //just because there is no isButtonJustPressed
                    //in future might add sounds for every block smth like: if(sound!=block.getSound()){ sound.pause(); sound=block.getSound();}

                }

               // if (block instanceof Destroyable && Gdx.input.isKeyPressed(Input.Keys.M))  //if you want want much faster mining just press M
                   // ((Destroyable) block).changeDurability(-20000, null); //be careful here passing null

                if (block instanceof Destroyable && ((Destroyable) block).isDestroyed()) {
                    remainingMiningTime = 0f; //after destroying
                    world.setBlock(x, y, new Void());                                                                       //"destroy" the block, i.e. set to Void
                    if (block instanceof Harvestable && ((Harvestable) block).checkTool(player.getEquipment().getItem())) {
                        player.getEquipment().addItem(((Harvestable) block).getItem(), ((Harvestable) block).getQuantity());  //harvest the block, giving its item to the player
                    }
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.E) || player.getHp().isDead()) //stop soundsefects if enetering menues/eq
        {
            remainingMiningTime = 0;
            miningSound.pause();
        }

        if(player.getHp().isDead())
            screenManager.enterGameScreen(GameOverScreen.ID, new NullTransition(), new NullTransition());

        if((Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) )) {
            screenManager.enterGameScreen(InGameMenuScreen.ID, new NullTransition(), new NullTransition());
            world.saveToDisk();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E) )
            screenManager.enterGameScreen(EquipmentScreen.ID, new NullTransition(), new NullTransition());

        //if(Gdx.input.isKeyJustPressed(Input.Keys.C))
         //  player.wypiszLewyDolnyRog();
        //if(Gdx.input.isKeyJustPressed(Input.Keys.S))

        if(Gdx.input.isKeyJustPressed(Input.Keys.L))
            player.getEquipment().addItem(new LazrPickaxe(), 1);


        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float x= floor((Gdx.input.getX()/scale + transX)/(Block.PIXEL_COUNT)+(posX)); //
            float y= floor((Gdx.input.getY()/scale + transY)/(Block.PIXEL_COUNT)+(posY)); //more elegant
            if(player.isReachable(world.getRectangle((int)x,(int)y),player.getEquipment().isSolid()) || Gdx.input.isKeyPressed(Input.Keys.G) )
             if(!world.isBlockOccupied((int)x,(int)y) && player.getEquipment().isSolid() )
                 world.setBlock((int)x, (int)y, player.getChooseBlock());
        }

    }
    private void soundHandler(float delta)
    {
        remainingMiningTime-=delta;
        if(remainingMiningTime>0)
            miningSound.resume();
        else
            miningSound.pause();
    }

}
