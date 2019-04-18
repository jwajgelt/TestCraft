package testcraft;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;

import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

public abstract class Block {

    static final float PIXEL_COUNT = 16f;   //length of a square block's side

    static private Sprite[] blockSprites;
    static private String blockName;
    private CollisionBox collisionBox;

    CollisionBox getCollisionBox() {
        return collisionBox;
    }

    public abstract Sprite getBlockSprite();

    @Override
    public String toString(){
        return blockName;
    }

}
