package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.GameItem;
import testcraft.items.Axe;

public class LogBlock extends CollectibleBlock {

    private static Texture texture = new Texture("LogBlock.png");
    private static int Id = 6;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Log Block";

    private static float maxDurability=50;
    private float durability=50;


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
        return new LogBlock();
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
