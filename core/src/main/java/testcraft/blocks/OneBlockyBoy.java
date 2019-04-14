package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class OneBlockyBoy extends Block {

    private final static OneBlockyBoy instance = new OneBlockyBoy();

    private OneBlockyBoy(){
        blockSprite = new Sprite(new Texture("OneBlockyBoy.png"));
        blockName = "One Blocky Boi";
    }

    public static OneBlockyBoy getInstance(){
        return instance;
    }

}
