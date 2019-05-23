package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class DiamondPickaxe extends Pickaxe {

    private static Texture texture = new Texture("DiamPick.png");

    {
        pickaxeCoefficient = 3f;
        name = "Diamond Pickaxe";
    }

    @Override
    public Material getMaterial(){
        return Material.DIAMOND;
    }

    @Override
    public int getId() {
        return 101;
    }

    @Override
    public Texture getTexture(){
        return texture;
    }
}
