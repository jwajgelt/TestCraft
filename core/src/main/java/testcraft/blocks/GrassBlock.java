package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class GrassBlock extends Block {

    private static int Id = 9;
    private static Texture texture = new Texture("GrassyBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Grass";

    public GrassBlock(){}

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Block getNewBlock() {
        return new GrassBlock();
    }

    @Override
    public int getId() {
        return Id;
    }
}
