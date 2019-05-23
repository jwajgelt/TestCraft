package testcraft.items;

public interface ToolItem extends GameItem {

    enum Material{
        IRON,
        DIAMOND,
        LAZR
    }

    Material getMaterial();

}
