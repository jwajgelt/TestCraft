package testcraft;

import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.core.serialization.annotation.Field;
import testcraft.blocks.*;
import testcraft.blocks.Void;

import java.io.Serializable;
import java.util.*;

class WorldChunk implements Serializable {

    static int CHUNK_SIZE = 64;

    private Block[][] blocks;                   //array containing chunk's blocks' information first coordinate is X, second Y

    final int chunkPosX, chunkPosY;      //chunk's left top corner's world coordinates

    private static Random randy = new Random();     //for testing purposes only

    private Block getBlock(int yPos)
    {
        if(yPos<2)
            return  new Void();
        return (randy.nextBoolean()) ? (randy.nextBoolean() ?  new DirtBlock() : new CoalBlock()) : randy.nextBoolean() ? new DirtBlock() : new CobblestoneBlock(randy.nextInt(10));
    }

    WorldChunk(int xPos, int yPos, Block[][] blocks){
        this.blocks = blocks;
        chunkPosX = xPos;
        chunkPosY = yPos;

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = getBlock(yPos+j);

    }


    void renderChunk(Graphics g, float shiftX, float shiftY){
        /*
         * Draw each block at integer coordinate.
         *
         */
        for(int i = 0; i < blocks.length; i++){
            for(int j = 0; j < blocks[i].length; j++){

                //calculate blocks world coordinates
                float posX = (chunkPosX - shiftX + i)*Block.PIXEL_COUNT;
                float posY = (chunkPosY - shiftY + j)*Block.PIXEL_COUNT;

                if(posX < -Block.PIXEL_COUNT || posX > InGameScreen.WIDTH
                || posY < -Block.PIXEL_COUNT || posY > InGameScreen.HEIGHT)
                    continue;   //don't render things off-screen

                //get block sprite and set sprite coordinates
                Sprite blockSprite = blocks[i][j].getBlockSprite();
                blockSprite.setPosition(posX+2, posY+2);
                blockSprite.setScale(2f);
                //finally, draw the sprite
                g.drawSprite(blockSprite);
            }
        }
    }
    Rectangle getRectangle(int a, int b)
    {

        return new Rectangle((chunkPosX+a)*Block.PIXEL_COUNT,(chunkPosY+b)*Block.PIXEL_COUNT, 1*Block.PIXEL_COUNT,1 *Block.PIXEL_COUNT);
    }

    Block setVoid (int a, int b)
    {
        Block X= blocks[a][b];
        blocks[a][b]=new Void();
        return X;
    }

    void setBlock(int a, int b, Block c){
        if(c!=null)
            blocks[a][b]=c;

    }

    boolean isBlockSolid(int a, int b){ return blocks[a][b].isSolid(); }

}
