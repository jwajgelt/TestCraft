package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.BlockItem;

import java.io.Serializable;

public class CoalBlock extends Block implements Serializable {

    static private Texture texture = new Texture("CoalBlock.png");
    static private int Id = 3;
    static private Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    static private String blockName = "Coal Block";

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

    public CoalBlock(){
        super();
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
        return new CoalBlock();
    }

}
