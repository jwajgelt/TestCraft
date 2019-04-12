package testcraft;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.blocks.OneBlockyBoy;
import testcraft.blocks.Void;

import java.io.Serializable;

import java.util.Random;

class WorldChunk implements Serializable {

    private Block[][] blocks;
    private final float chunkPosX, chunkPosY;
    private Random randy = new Random();

    WorldChunk(float xPos, float yPos, Block[][] blocks){
        this.blocks = blocks;
        chunkPosX = xPos;
        chunkPosY = yPos;

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = (randy.nextBoolean()) ? new Void() : new OneBlockyBoy();
    }

    void renderChunk(Graphics g){
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
                float posX = (chunkPosX + j)*Block.PIXEL_COUNT;
                float posY = (chunkPosY + i)*Block.PIXEL_COUNT;

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

}
