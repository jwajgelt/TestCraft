package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class OneBlockyBoy extends CollectibleBlock {

    static int Id = 420;
    static private Texture texture = new Texture("OneBlockyBoy.png");
    static private Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    static String blockName = "BlockyBoy";

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
    public Block getNewBlock(){
        return new OneBlockyBoy();
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
