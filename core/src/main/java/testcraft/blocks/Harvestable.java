package testcraft.blocks;

import testcraft.BlockItem;
import testcraft.GameItem;

//interface representing blocks which can be harvested (they give an item upon destruction)

public interface Harvestable extends Destroyable {

    public BlockItem getItem();

    public boolean checkTool(GameItem tool);      //to be changed to ToolItem once implemented

}
