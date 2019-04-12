package testcraft.blocks;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.Block;

public class Void extends Block {

    {
        blockSprite = new Sprite(new Texture("OneBlockyBoy.png"));
        blockSprite.setAlpha(0f);
    }

}
