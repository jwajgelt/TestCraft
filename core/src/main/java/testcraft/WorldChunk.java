package testcraft;

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

    private Block getBlock()
    {
        return (randy.nextBoolean()) ? (randy.nextBoolean() ? new Void() : new CoalBlock()) : randy.nextBoolean() ? new DirtBlock() : new CobblestoneBlock(randy.nextInt(10));
    }

    WorldChunk(int xPos, int yPos, Block[][] blocks){
        this.blocks = blocks;
        chunkPosX = xPos;
        chunkPosY = yPos;

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = getBlock();
    }


    void renderChunk(Graphics g, float shiftX, float shiftY){
        /*
         * Remember the old scaling to restore later.
         *
         */
        float oldX = g.getScaleX(), oldY = g.getScaleY();
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
                blockSprite.setPosition(posX+1, posY+1);
                blockSprite.setScale(2f);
                //finally, draw the sprite
                g.drawSprite(blockSprite);
            }
        }
        /*
         * Restoring the old scaling after rendering the chunk.
         *
         */
        g.scale(oldX, oldY);
    }

    public void setVoid (int a, int b)
    {
        blocks[a][b]=new Void();
    }

}
