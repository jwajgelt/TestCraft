package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.util.Random;

public class GrassBlock extends Block implements Destroyable {

    private static int Id = 9;
    private static Texture texture = new Texture("GrassyBlock.png");
    private static Sprite[] blockSprites=new Sprite[]{new Sprite(texture), new Sprite(new Texture("GrassyBlock2.png"))};
    private static String blockName = "Grass";
    private int timer=7;
    private int pickSprite=0;

    public GrassBlock(){}

    @Override
    public void update(){
        if(timer>0)
            timer--;
        else {
            timer=new Random().nextInt(10)+5;
            pickSprite=(pickSprite+1)%2;
        }
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Sprite getBlockSprite() {
        return blockSprites[pickSprite];
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Block getNewBlock() {
        return new GrassBlock();
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public float getDurability() {
        return 0;
    }
}
