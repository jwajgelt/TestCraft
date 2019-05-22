package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class IronShovel extends Shovel {

    {
        shovelCoefficient = 1.5f;
    }

    static private Texture texture = new Texture("IronShvl.png");

    @Override
    public Material getMaterial() {
        return Material.IRON;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getId() {
        return 104;
    }

}
