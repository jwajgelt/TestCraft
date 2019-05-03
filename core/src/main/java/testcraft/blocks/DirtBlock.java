package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;

public class DirtBlock extends Block implements Serializable {


    private static Texture texture = new Texture("DirtBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(new Texture("DirtBlock.png"))};
    private static String blockName = "Dirt";

    public DirtBlock(){ }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }
    @Override
    public  Texture getTexture()
    {
        return texture;
    }
}
