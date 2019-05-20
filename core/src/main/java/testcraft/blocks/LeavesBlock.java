package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.util.Random;

public class LeavesBlock extends Block implements Destroyable {

    private static Random random=new Random();
    private static Texture texture = new Texture("LeavesBlock.png");
    private static int Id = 5;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture), new Sprite(new Texture("LeavesBlock2.png"))};
    private static String blockName = "Leaves Block";
    private int timer=10;
    private int pickSprite=0;
    private  float durability=15;


    public LeavesBlock(){

    }

    @Override
    public void update(){
        if(timer>0)
            timer--;
        else {
            timer= random.nextInt(8)+4;
            pickSprite=(pickSprite+1)%2;
        }
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[pickSprite];
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Block getNewBlock() {
        return new LeavesBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override public float getDurability()
    {
        return  durability;
    }
    @Override
    public boolean isDestroyed()
    {
        return durability<0;
    }
    @Override
    public float changeDurability(float delta)//changes Durability, and returns it
    {return durability+=delta;}
}
