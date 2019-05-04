package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.BlockItem;

public class WoodBlock extends Block {

    static private int Id = 4;
    private static Texture texture = new Texture("WoodBlock.png");
    private static Sprite[] blockSprites = new Sprite[]{new Sprite(texture)};
    private static String blockName = "Wood";

    {
        item = new BlockItem() {

            @Override
            public Texture getTexture() {
                return texture;
            }

            @Override
            public int getId() {
                return Id;
            }

            @Override
            public Block getBlock() {
                return getNewBlock();
            }

        };
    }

    public WoodBlock(){
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Block getNewBlock(){
        return new WoodBlock();
    }

}
