package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class CobblestoneBlock extends Block {

    private final static CobblestoneBlock instance = new CobblestoneBlock();

    private CobblestoneBlock(){
        blockSprite = new Sprite(new Texture("CobblestoneBlock.png"));
        blockName = "CobbleStone";
    }

    public static CobblestoneBlock getInstance(){ return instance; }
}
