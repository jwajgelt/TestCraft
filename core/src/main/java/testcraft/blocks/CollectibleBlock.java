package testcraft.blocks;

import testcraft.Block;
import testcraft.BlockItem;
import testcraft.GameItem;
import testcraft.ItemPicker;

//block, which upon harvesting gives its BlockItem

public abstract class CollectibleBlock extends Block implements Harvestable {

    @Override
    public BlockItem getItem(){
        return ItemPicker.getBlockItem(this);
    }

    @Override
    public int getQuantity() {
        return 1;
    }

    @Override
    public boolean checkTool(GameItem tool) {
        return true;
    }

}
