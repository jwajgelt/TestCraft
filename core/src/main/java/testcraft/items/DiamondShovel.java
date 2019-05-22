package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class DiamondShovel extends Shovel {

    {
        shovelCoefficient = 3f;
    }

    static private Texture texture = new Texture("DiamShvl.png");

    @Override
    public Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getId() {
        return 105;
    }

}
