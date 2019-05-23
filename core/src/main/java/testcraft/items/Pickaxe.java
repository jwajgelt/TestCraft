package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Pickaxe implements ToolItem {
    protected float pickaxeCoefficient = 1f;
    protected Texture texture;
    protected String name;

    public float getPickaxeCoefficient(){
        return pickaxeCoefficient;
    }

    public String getName(){
        return name;
    }
}
