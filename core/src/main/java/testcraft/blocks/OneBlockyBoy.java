package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class OneBlockyBoy extends Block {

    static  private  Texture texture = new Texture("OneBlockyBoy.png");
    static private Sprite[] blockSprites=new Sprite[]{new Sprite(new Texture("OneBlockyBoy.png"))};
    static private String blockName = "BlockyBoy";

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
