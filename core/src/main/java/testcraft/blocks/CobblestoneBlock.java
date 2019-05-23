package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.items.GameItem;
import testcraft.items.Pickaxe;

import java.io.Serializable;
import java.util.Random;

public class CobblestoneBlock extends CollectibleBlock implements Serializable {

    private static Random random=new Random();
    private static int Id = 2;
    protected static final String blockName = "Cobblestone Block";
    private static Texture texture = new Texture("CobblestoneBlock1.png");
    private static Sprite[] blockSprites=new Sprite[]{
            new Sprite(texture),
            new Sprite(new Texture("CobblestoneBlock2.png"))};

    private byte chooseSprite; //to make serialization a bit faster
    private static float maxDurability=100;
    protected float durability=maxDurability;

    private CobblestoneBlock(byte i){

        chooseSprite=(byte)(i%blockSprites.length);
    }

    public CobblestoneBlock()
    {
        this((byte)random.nextInt(2));
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[chooseSprite];
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Block getNewBlock(){
        return new CobblestoneBlock();
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
    public boolean checkTool(GameItem tool){
        return tool instanceof Pickaxe;
    }

    @Override
    public float changeDurability(float delta, GameItem gameItem){
        float coefficients = 1;
        if(gameItem instanceof Pickaxe){
            coefficients *= ((Pickaxe)gameItem).getPickaxeCoefficient();
        }
        return durability+=delta*coefficients*70;
    }  //changes Durability, and returns it

    @Override
    public float getDurabilityPercentage(){ return durability/maxDurability;}
}
