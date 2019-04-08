package testcraft;

import org.mini2Dx.core.graphics.Sprite;

public abstract class Block {

    protected Sprite blockSprite;

    Sprite getBlockSprite(){
        return new Sprite(blockSprite);
    }

}
