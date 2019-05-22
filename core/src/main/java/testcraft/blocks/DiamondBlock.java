package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.GameItem;
import testcraft.items.Pickaxe;

import java.io.Serializable;

public class DiamondBlock extends CollectibleBlock implements Serializable {



    private static Texture texture = new Texture("DiamondBlock.png");
    private static int Id = 17;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Diamond Block";

    private static float maxDurability=300;
    private float durability=maxDurability;

    public DiamondBlock(){}

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
    public Block getNewBlock() {
        return new DiamondBlock();
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

