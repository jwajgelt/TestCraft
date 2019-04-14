package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class CoalBlock extends Block {

    private final static CoalBlock instance = new CoalBlock();

    private CoalBlock(){
        blockSprite = new Sprite(new Texture("CoalBlock.png"));
        blockName = "Coal Block";
    }

    public static CoalBlock getInstance(){ return instance; }
}
