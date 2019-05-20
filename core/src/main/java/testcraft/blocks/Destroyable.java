package testcraft.blocks;

//interface representing items which can be destroyed by the player

import testcraft.GameItem;

public interface Destroyable {

    public float getDurability();
    public boolean isDestroyed();
    public float changeDurability(float delta, GameItem gameItem); //changes Durability, and returns it

}
