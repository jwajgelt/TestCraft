package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;

public class IronBlock extends CollectibleBlock implements Serializable {


    private static Texture texture = new Texture("IronBlock.png");
    private static int Id = 10;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Iron Block";

    public IronBlock(){}

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
        return new IronBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public float getDurability() {
        return 0;
    }
}
