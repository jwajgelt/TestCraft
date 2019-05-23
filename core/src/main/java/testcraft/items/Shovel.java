package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Shovel implements ToolItem {
    protected float shovelCoefficient = 1f;
    protected Texture texture;
    protected String name;

    public float getShovelCoefficient(){
        return shovelCoefficient;
    }

    public String getName(){
        return name;
    }
}
