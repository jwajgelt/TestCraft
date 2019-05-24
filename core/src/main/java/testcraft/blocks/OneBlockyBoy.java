package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.items.GameItem;

public class OneBlockyBoy extends Block {

    static int Id = 420;
    static private Texture texture = new Texture("VoidBlock.png");
    static private Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    protected static final String blockName = "BlockyBoy";
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
    public Block getNewBlock(){
        return new OneBlockyBoy();
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public String toString(){
        return blockName;
    }

}
