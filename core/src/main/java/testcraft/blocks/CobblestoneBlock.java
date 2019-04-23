package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;

public class CobblestoneBlock extends Block implements Serializable {


    private static Sprite[] blockSprites=new Sprite[]{
            new Sprite(new Texture("CobblestoneBlock1.png")),
            new Sprite(new Texture("CobblestoneBlock2.png"))};

    private int chooseSprite;

    public CobblestoneBlock(int i){
        chooseSprite=i%blockSprites.length;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[chooseSprite];
    }
}
