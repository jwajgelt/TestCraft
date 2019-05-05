package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Void extends Block  implements Serializable {

    private static int Id = 0;
    private static Texture texture = new Texture("VoidBlock.png");
    private static Sprite[] blockSprites = new Sprite[]{new Sprite(texture)};
    static String blockName = "Void";

    public Void(){

    }

    @Override
    public boolean isSolid() {
        return false;
    }

    public Sprite getBlockSprite() {
        return blockSprites[0];
    }

    @Override
    public Block getNewBlock(){
        return new Void();
    }

    @Override
    public int getId() {
        return Id;
    }

}
