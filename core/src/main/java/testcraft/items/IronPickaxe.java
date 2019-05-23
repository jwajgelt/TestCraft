package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public class IronPickaxe extends Pickaxe {

    private static Texture texture = new Texture("IronPick.png");

    {
        pickaxeCoefficient = 1.5f;
        name = "Iron Pickaxe";
    }

    @Override
    public Material getMaterial(){
        return Material.IRON;
    }

    @Override
    public int getId() {
        return 100;
    }

    @Override
    public Texture getTexture(){
        return texture;
    }
}
