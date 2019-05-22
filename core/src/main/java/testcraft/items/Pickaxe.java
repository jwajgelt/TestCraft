package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Pickaxe implements ToolItem {
    protected float pickaxeCoefficient = 1f;
    protected Texture texture;

    public float getPickaxeCoefficient(){
        return pickaxeCoefficient;
    }
}
