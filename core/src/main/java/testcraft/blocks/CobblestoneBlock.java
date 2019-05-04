package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;
import testcraft.BlockItem;

import java.io.Serializable;

public class CobblestoneBlock extends Block implements Serializable {

    static private int Id = 2;
    private static Texture texture = new Texture("CobblestoneBlock1.png");
    private static Sprite[] blockSprites=new Sprite[]{
            new Sprite(texture),
            new Sprite(new Texture("CobblestoneBlock2.png"))};

    private int chooseSprite;

    {
        item = new BlockItem() {

            @Override
            public Texture getTexture() {
                return texture;
            }

            @Override
            public int getId() {
                return Id;
            }

            @Override
            public Block getBlock() {
                return getNewBlock();
            }

        };
    }

    public CobblestoneBlock(int i){
        chooseSprite=i%blockSprites.length;
    }

    public CobblestoneBlock ()
    {
        this(0);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[chooseSprite];
    }

    @Override
    public Block getNewBlock(){
        return new CobblestoneBlock();
    }
}
