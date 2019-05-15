package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class LeavesBlock extends Block implements Destroyable {

    private static Texture texture = new Texture("LeavesBlock.png");
    private static int Id = 5;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Leaves Block";

    public LeavesBlock(){

    }

    @Override
    public boolean isSolid() {
        return false;
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
        return new LeavesBlock();
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
