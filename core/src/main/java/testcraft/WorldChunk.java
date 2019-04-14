package testcraft;

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.core.serialization.annotation.Field;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.CobblestoneBlock;
import testcraft.blocks.OneBlockyBoy;
import testcraft.blocks.Void;

import java.util.*;

class WorldChunk {

    static int CHUNK_SIZE = 64;

    private Block[][] blocks;                   //array containing chunk's blocks' information

    @Field final int chunkPosX, chunkPosY;      //chunk's left top corner's world coordinates

    private static Random randy = new Random();     //for testing purposes only

    WorldChunk(int xPos, int yPos, Block[][] blocks){
        this.blocks = blocks;
        chunkPosX = xPos;
        chunkPosY = yPos;

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = (randy.nextBoolean()) ? (randy.nextBoolean() ? Void.getInstance() : CoalBlock.getInstance()) : randy.nextBoolean() ? OneBlockyBoy.getInstance() : CobblestoneBlock.getInstance();
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
                float posX = (chunkPosX + shiftX + j)*Block.PIXEL_COUNT;
                float posY = (chunkPosY + shiftY + i)*Block.PIXEL_COUNT;

                //get block sprite and set sprite coordinates
                Sprite blockSprite = blocks[i][j].getBlockSprite();
                blockSprite.setPosition(posX, posY);

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

    String getJson() throws SerializationException{
        String jsonChunk;
        jsonChunk = Mdx.json.toJson(this);
        return jsonChunk;
    }

}
