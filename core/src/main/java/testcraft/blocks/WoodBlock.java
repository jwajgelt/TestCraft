package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.GameItem;

public class WoodBlock extends CollectibleBlock {

    private static int Id = 4;
    private static Texture texture = new Texture("WoodBlock.png");
    private static Sprite[] blockSprites = new Sprite[]{new Sprite(texture)};
    private static String blockName = "Wood";
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
    public float getDurability()
    {
        return  durability;
    }

    @Override
    public boolean isDestroyed() { return durability<=0; }

    @Override
    public float changeDurability(float delta, GameItem gameItem){return durability+=delta*70;}  //changes Durability, and returns it

}
