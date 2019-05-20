package testcraft.blocks;

import testcraft.Block;
import testcraft.BlockItem;
import testcraft.GameItem;
import testcraft.ItemPicker;

//block, which upon harvesting gives its BlockItem

public abstract class CollectibleBlock extends Block implements Harvestable {

    protected  float durability;

    CollectibleBlock()
    {
        durability=100;
    }
    CollectibleBlock(float durability)
    {
        this.durability=durability;
    }

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

    @Override
    public float getDurability()
    {
        return  durability;
    }

    @Override
    public boolean isDestroyed() { return durability<=0; }

    @Override
    public float changeDurability(float delta){return durability+=delta;}  //changes Durability, and returns it



}
