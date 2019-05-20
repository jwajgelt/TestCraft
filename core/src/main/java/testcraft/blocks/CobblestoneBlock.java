package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;
import java.util.Random;

public class CobblestoneBlock extends CollectibleBlock implements Serializable {

    private static Random random=new Random();
    private static int Id = 2;
    private static Texture texture = new Texture("CobblestoneBlock1.png");
    private static Sprite[] blockSprites=new Sprite[]{
            new Sprite(texture),
            new Sprite(new Texture("CobblestoneBlock2.png"))};

    private int chooseSprite;

    public CobblestoneBlock(int i){

        super(100);
        chooseSprite=i%blockSprites.length;
    }

    public CobblestoneBlock()
    {
        this(random.nextInt(2));
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
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Block getNewBlock(){
        return new CobblestoneBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

}
