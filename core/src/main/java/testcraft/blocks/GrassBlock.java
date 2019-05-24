package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.items.GameItem;

import java.util.Random;

public class GrassBlock extends Block implements Destroyable {

    private static Random random=new Random();
    private static int Id = 9;
    private static Texture texture = new Texture("GrassyBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture), new Sprite(new Texture("GrassyBlock2.png")),};
    protected static final String blockName = "Grass";
    private byte timer=6;
    private byte pickSprite=0;
    private float durability=15;

    public GrassBlock(){

    }

    @Override
    public boolean isOccupied(){return false;}

    @Override
    public void update(){
        if(timer>0)
            timer--;
        else {
            timer=(byte)(random.nextInt(6)+4);
            pickSprite=(byte)((pickSprite+1)%2);
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
        return new GrassBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public String toString(){
        return blockName;
    }

    @Override
    public float getDurability()
    {
        return  durability;
    }

    @Override
    public boolean isDestroyed() { return durability<=0; }

    @Override
    public float changeDurability(float delta, GameItem gameItem){return durability+=delta*70;}  //changes Durability, and returns it
}
