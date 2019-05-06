package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class WoodBlock extends Block {

    private static int Id = 4;
    private static Texture texture = new Texture("WoodBlock.png");
    private static Sprite[] blockSprites = new Sprite[]{new Sprite(texture)};
    private static String blockName = "Wood";

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

    @Override
    public int getId() {
        return Id;
    }

}
