package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class DirtBlock extends Block {

    private final static DirtBlock instance = new DirtBlock();

    private DirtBlock(){
        blockSprite = new Sprite(new Texture("DirtBlock.png"));
        blockName = "Dirt";
    }

    public static DirtBlock getInstance(){
        return instance;
    }

}
