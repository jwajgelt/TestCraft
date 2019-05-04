package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.BlockItem;

public class OneBlockyBoy extends Block {

    static int Id = 420;
    static private Texture texture = new Texture("OneBlockyBoy.png");
    static private Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    static String blockName = "BlockyBoy";

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
        return new OneBlockyBoy();
    }
}
