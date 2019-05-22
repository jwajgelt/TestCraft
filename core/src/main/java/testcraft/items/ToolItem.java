package testcraft.items;

import testcraft.GameItem;

public interface ToolItem extends GameItem {

    enum Material{
        IRON,
        DIAMOND,
        LAZR
    }

    Material getMaterial();

}
