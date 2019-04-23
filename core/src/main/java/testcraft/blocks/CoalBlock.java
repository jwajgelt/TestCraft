package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;

public class CoalBlock extends Block implements Serializable {

    static private Sprite[] blockSprites=new Sprite[]{new Sprite(new Texture("CoalBlock.png"))};
    static private String blockName = "Coal Block";
    private CollisionBox collisionBox;

    public CoalBlock(){

    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }
}
