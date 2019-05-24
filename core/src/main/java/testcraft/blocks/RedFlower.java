package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class RedFlower extends Flower {

    private static int Id = 21;
    private static Texture texture = new Texture("RedFlower.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    protected static final String blockName = "Red Flower";

    public RedFlower(){}

    @Override
    public Block getNewBlock() {
        return new RedFlower();
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
    public int getId() {
        return Id;
    }
}
