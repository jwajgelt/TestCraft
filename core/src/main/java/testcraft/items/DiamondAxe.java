package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class DiamondAxe extends Axe {

    {
        axeCoefficient = 3f;
        name = "Diamond Axe";
    }

    static private Texture texture = new Texture("DiamAxe.png");

    @Override
    public ToolItem.Material getMaterial() {
        return Material.DIAMOND;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getId() {
        return 103;
    }

}
