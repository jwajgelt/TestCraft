package testcraft;


import com.badlogic.gdx.graphics.Texture;
import testcraft.items.*;

import java.io.Serializable;

import static org.apache.commons.lang3.ObjectUtils.min;

public class Equipment implements Serializable {

    private int chosenItem=0;
    private final int maxNumberOfItmes=999;
    private GameItem[] items = new GameItem[36];
    private int[] quantity = new int[36];
    Equipment () {
        try {
            items[0] = new IronPickaxe();
            quantity[0]=1;
            items[1] = new DiamondPickaxe();
            quantity[1]=1;
            items[2] = new IronAxe();
            quantity[2]=1;
            items[3] = new DiamondAxe();
            quantity[3]=1;
            items[4] = new IronShovel();
            quantity[4]=1;
            items[5] = new DiamondShovel();
            quantity[5]=1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void addItem(GameItem item, int qua) {
        if(item==null)
            return;

        for (int i=0;i<36;i++)
            if(items[i]!=null && item.getId()==items[i].getId())
            {
                quantity[i]=min(quantity[i]+qua, maxNumberOfItmes);
                return;
            }
        for (int i=0;i<36;i++)
            if(items[i]==null)
            {
                quantity[i]=min(qua, maxNumberOfItmes);
                items[i]=item;
                return;
            }
    }

    void removeItem(int qua) {
        removeItem(chosenItem, qua);
    }
    boolean isSolid ()
    {
        return (getItem()!=null && getItem() instanceof  BlockItem/* && !((BlockItem)getItem()).getBlock().isOccupied()*/);

    }

    GameItem getItem() {
        return getItem(chosenItem);
    }

    void setChosenItem(int pos) {
        chosenItem=pos;
    }

    void removeItem(int pos, int qua) {
        quantity[pos]-=qua;
        if(quantity[pos]<=0) {
            items[pos] = null;
            quantity[pos] = 0;
        }
    }

    boolean available(int pos) {
        return items[pos]!=null;
    }

    GameItem getItem(int pos) {
        try {
            return available(pos) ? items[pos] : null;
        }
        catch (Exception e)
        {
            System.out.println("Failed");
            e.printStackTrace();
            return null;
        }
    }

    Texture getTexture (int pos) {
        try {
            return available (pos) ? items[pos].getTexture(): null;
        }
        catch (Exception e)
        {
            System.out.println("Failed");
            e.printStackTrace();
            return null;
        }
    }

    int getQuantity(int pos) {
        return quantity[pos];
    }

    int getChosenItem() {
        return chosenItem;
    }

    String getItemName()
    {
        return available(chosenItem) ? getItem().getName() : "No item";
    }


}
