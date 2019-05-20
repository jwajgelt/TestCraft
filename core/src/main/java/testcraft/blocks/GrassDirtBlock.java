package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

public class GrassDirtBlock extends DirtBlock {

    //private static int Id = 8;
    private static Texture grassTexture = new Texture("GrassDirtBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(grassTexture)};
    private static String blockName = "Grass Block";

    public GrassDirtBlock(){super();}

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

}
