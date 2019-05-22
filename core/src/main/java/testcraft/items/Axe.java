package testcraft.items;

import com.badlogic.gdx.graphics.Texture;

public abstract class Axe implements ToolItem {
    protected Texture texture;
    protected float axeCoefficient;

    public float getAxeCoefficient() {
        return axeCoefficient;
    }
}
