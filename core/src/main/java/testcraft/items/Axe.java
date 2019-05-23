package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Axe implements ToolItem {
    protected Texture texture;
    protected float axeCoefficient;
    protected String name;

    public float getAxeCoefficient() {
        return axeCoefficient;
    }

    public String getName(){
        return name;
    }
}
