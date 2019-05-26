package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class YellowFlower extends Flower {
    private static int Id = 23;
    private static Texture texture = new Texture("YellowFlower.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    protected static final String blockName = "Yellow Flower";

    public YellowFlower(){}

    @Override
    public Block getNewBlock() {
        return new YellowFlower();
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
    @Override
    public String toString(){
        return blockName;
    }
}
