package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Shovel implements ToolItem {
    protected float shovelCoefficient = 1f;
    protected Texture texture;

    public float getShovelCoefficient(){
        return shovelCoefficient;
    }
}
