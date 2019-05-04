package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.BlockItem;

import java.io.Serializable;

public class DirtBlock extends Block implements Serializable {

    static private int Id = 1;
    private static Texture texture = new Texture("DirtBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Dirt";

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

    public DirtBlock(){
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
        return new DirtBlock();
    }
}
