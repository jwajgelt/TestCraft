package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;

public class CoalBlock extends Block implements Serializable {

    private static Texture texture = new Texture("CoalBlock.png");
    private static int Id = 3;
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture)};
    private static String blockName = "Coal Block";

    public CoalBlock(){
        super();
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Block getNewBlock(){
        return new CoalBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

}
