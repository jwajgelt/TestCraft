package testcraft;

import org.mini2Dx.core.graphics.Sprite;

public abstract class Block {

    static final float PIXEL_COUNT = 16f;   //length of a square block's side

    protected Sprite blockSprite;

    Sprite getBlockSprite(){
        return new Sprite(blockSprite);
    }

}
