package testcraft.blocks;

//interface representing items which can be destroyed by the player

public interface Destroyable {

    public float getDurability();
    public boolean isDestroyed();
    public float changeDurability(float delta); //changes Durability, and returns it

}
