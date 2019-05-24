package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class BlueFlower extends Flower {
    private static int Id = 22;
    private static Texture texture = new Texture("BlueFlower.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    protected static final String blockName = "Blue Flower";

    public BlueFlower(){}

    @Override
    public Block getNewBlock() {
        return new BlueFlower();
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
