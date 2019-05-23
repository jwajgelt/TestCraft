package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.items.GameItem;
import testcraft.items.Axe;

public class WoodBlock extends CollectibleBlock {

    private static int Id = 4;
    private static Texture texture = new Texture("WoodBlock.png");
    private static Sprite[] blockSprites = new Sprite[]{new Sprite(texture)};
    protected static final String blockName = "Wood";

    private static float maxDurability=45;
    private  float durability=45;


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
        return new WoodBlock();
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
    public float changeDurability(float delta, GameItem gameItem){
        float coefficient = 1f;
        if(gameItem instanceof Axe){
            coefficient *= ((Axe)gameItem).getAxeCoefficient();
        }
        return durability+=delta*coefficient*70;
    }  //changes Durability, and returns it

    @Override
    public float getDurabilityPercentage(){ return durability/maxDurability;}
}
