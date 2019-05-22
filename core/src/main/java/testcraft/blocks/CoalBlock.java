package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.GameItem;
import testcraft.items.Pickaxe;

import java.io.Serializable;

public class CoalBlock extends CollectibleBlock implements Serializable {

    private static Texture texture = new Texture("CoalBlock.png");
    private static int Id = 3;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Coal Block";
    private static float maxDurability=100;
    protected  float durability=maxDurability;


    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Block getNewBlock(){
        return new CoalBlock();
    }

    @Override
    public int getId() {
        return Id;
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
