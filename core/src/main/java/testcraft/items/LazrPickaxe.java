package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class LazrPickaxe extends Pickaxe {

    {
        pickaxeCoefficient = 1000000f;
        name = "LAZER Pickaxe";
    }

    private static Texture texture = new Texture("LazrPick.png");

    @Override
    public Material getMaterial() {
        return Material.LAZR;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getId() {
        return 1337;
    }
}
