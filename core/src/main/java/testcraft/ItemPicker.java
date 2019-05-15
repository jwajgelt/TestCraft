package testcraft;

import com.badlogic.gdx.graphics.Texture;
import testcraft.blocks.CollectibleBlock;

import java.util.Map;
import java.util.TreeMap;

public class ItemPicker {

    private static int BLOCK_ID_MIN = 0;
    private static int BLOCK_ID_MAX = 4;

    static private Map<Integer, GameItem> itemMap = new TreeMap<>();

    static public BlockItem getBlockItem(final CollectibleBlock block){
        final int id = block.getId();
        if(!itemMap.containsKey(id) || itemMap.get(id) == null)
            itemMap.put(id, new BlockItem(){

                @Override
                public Texture getTexture() {
                    return block.getTexture();
                }

                @Override
                public int getId() {
                    return id;
                }

                @Override
                public Block getBlock() {
                    return block.getNewBlock();
                }

            });
        return (BlockItem)itemMap.get(id);
    }

    static public class NoSuchGameItemException extends Exception{
        
    }

    static public GameItem getItem(int id) throws NoSuchGameItemException {
        if(itemExists(id)){
            return itemMap.get(id);
        }
        throw new NoSuchGameItemException();
    }

    static public boolean itemExists(int id){
        return itemMap.containsKey(id);
    }
}
