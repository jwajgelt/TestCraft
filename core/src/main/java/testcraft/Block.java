package testcraft;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

import java.io.Serializable;

public abstract class Block implements Serializable {

    static final float PIXEL_COUNT = 16f;   //length of a square block's side

    static int Id;
    static protected Texture texture;
    static protected Sprite[] blockSprites;
    static String blockName;

    protected BlockItem item;

    public abstract boolean isSolid();

    public abstract Sprite getBlockSprite();

    @Override
    public String toString(){
        return blockName;
    }

    public BlockItem getItem(){
        return item;
    }

    public abstract Block getNewBlock();

}