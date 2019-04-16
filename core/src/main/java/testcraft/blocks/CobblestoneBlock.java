package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class CobblestoneBlock extends Block {

    private final static CobblestoneBlock instance1 = new CobblestoneBlock("CobblestoneBlock1.png");
    private final static CobblestoneBlock instance2 = new CobblestoneBlock("CobblestoneBlock2.png");

    private CobblestoneBlock(String spriteName){
        blockSprite = new Sprite(new Texture(spriteName));
        blockName = "CobbleStone";
    }

    public static CobblestoneBlock getInstance(boolean t){
        return t? instance1 : instance2;
    }
}
