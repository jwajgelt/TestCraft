package testcraft;


import com.badlogic.gdx.graphics.Texture;
import testcraft.blocks.CoalBlock;

import java.io.Serializable;

import static org.apache.commons.lang3.ObjectUtils.min;

public class Equipment implements Serializable {

    private int chosenItem=0;
    private final int maxNumberOfItmes=999;
    private GameItem[] items = new GameItem[36];
    private int[] quantity = new int[36];
    Equipment () {
        try {
            items[0]= new CoalBlock().getItem();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        quantity[0]=123;
    }

    void addItem(GameItem item, int qua) {

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
        removeItem(chosenItem,qua);
    }

    GameItem getItem() {
        return getItem(chosenItem);
    }
    ///POKI CO WAZNE SA TYLKO TE WYZEJ

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
            return available(pos)? items[pos] : null;
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

    int getChosenItemn() {
        return chosenItem;
    }


}
