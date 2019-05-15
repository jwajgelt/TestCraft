package testcraft.blocks;

import testcraft.BlockItem;
import testcraft.GameItem;

//interface representing blocks which can be harvested (they give an item upon destruction)

public interface Harvestable extends Destroyable {

    //return harvested item and its quantity
    GameItem getItem();
    int getQuantity();

    boolean checkTool(GameItem tool);      //to be changed to ToolItem once implemented

}
