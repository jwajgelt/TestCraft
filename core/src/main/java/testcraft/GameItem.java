package testcraft;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public interface GameItem extends Serializable {

    Texture getTexture();

    int getId();

}
