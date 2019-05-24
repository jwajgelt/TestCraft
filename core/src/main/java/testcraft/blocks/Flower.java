package testcraft.blocks;

import testcraft.items.GameItem;

public abstract class Flower extends CollectibleBlock {

    private static float maxDurability=15;
    private float durability=maxDurability;

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean checkTool(GameItem tool) {
        return true;
    }

    @Override
    public float getDurability() {
        return durability;
    }

    @Override
    public boolean isDestroyed() {
        return durability<=0;
    }

    @Override
    public float changeDurability(float delta, GameItem gameItem) {
        return durability+=delta*70;
    }
}
