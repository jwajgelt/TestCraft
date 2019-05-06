package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class GrassDirtBlock extends Block {

    private static int Id = 8;
    private static Texture texture = new Texture("GrassDirtBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Grass Block";

    public GrassDirtBlock(){}

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Block getNewBlock() {
        return new GrassDirtBlock();
    }

    @Override
    public int getId() {
        return Id;
    }
}
