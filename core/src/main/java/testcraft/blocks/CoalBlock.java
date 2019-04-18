package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class CoalBlock extends Block {

    static private Sprite[] blockSprites=new Sprite[]{new Sprite(new Texture("CoalBlock.png"))};
    static private String blockName = "Coal Block";
    private CollisionBox collisionBox;

    public CoalBlock(){

    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[0];
    }
}
