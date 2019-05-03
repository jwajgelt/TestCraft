package testcraft;


import com.badlogic.gdx.graphics.Texture;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.Void;

import java.io.Serializable;

import static org.apache.commons.lang3.ObjectUtils.min;
import testcraft.blocks.*;

public class Equipment implements Serializable {

    private int chosenItem=0;
    private  int maxNumberOfItmes=999;
   private  Class<? extends GameItem>  items[] = new Class[36];
   private int [] quantity = new int [36];

   Equipment ()
   {
       try {
           items[0]=(Class<CoalBlock>)Class.forName("testcraft.blocks.CoalBlock");
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       quantity[0]=123;
   }

   void addItem(Class<? extends GameItem> item, int ile)
   {

     for (int i=0;i<36;i++)
         if(item.equals(items[i]))
         {

             quantity[i]=min(quantity[i]+ile,maxNumberOfItmes);
             return;
         }
       for (int i=0;i<36;i++)
           if(items[i]==null)
           {
               quantity[i]=min(ile, maxNumberOfItmes);
               items[i]=item;
               return;
           }
   }

   void removeItem(int ile) //
   {
       removeItem(chosenItem,ile);
   }

   GameItem getItem()
    {
        return getItem(chosenItem);
    }
    ///POKI CO WAZNE SA TYKO TE WYZEJ

   void setChosenItem(int pos)
    {
        chosenItem=pos;
    }



   void removeItem(int pos, int qua)
   {
       quantity[pos]-=qua;
       if(quantity[pos]<=0) {
           items[pos] = null;
           quantity[pos] = 0;
       }
   }
   boolean available(int pos)
   {
       return items[pos]!=null;
   }



    GameItem getItem(int pos)
   {
       try {
           return available(pos)? items[pos].newInstance() : null; //UWAGA
       }
       catch (Exception e)
       {
           System.out.println("Failed");
           e.printStackTrace();
           return null;
       }
   }
   Texture getTexture (int pos)
   {
       try {

           return available (pos) ? (Texture)items[pos].getMethod("getTexture").invoke(getItem(pos)): null;
       }
       catch (Exception e)
       {
           System.out.println("Failed");
           e.printStackTrace();
           return null;
       }
   }
   int getQuantity(int pos)
   {return  quantity[pos];}
   int getChosenItemn()
   {return chosenItem;}


}
