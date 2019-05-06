package testcraft;

import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.blocks.Void;

import java.io.Serializable;

public class WorldChunk implements Serializable {

    public static int CHUNK_SIZE = 64;

    protected Block[][] blocks;                       //array containing chunk's blocks' information first coordinate is X, second Y

    final int chunkPosX, chunkPosY;                 //chunk's left top corner's world coordinates

    public WorldChunk(int xPos, int yPos, Block[][] blocks){
        this.blocks = blocks;
        chunkPosX = xPos;
        chunkPosY = yPos;

        for(int i = 0; i < blocks.length; i++)
            for(int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = new Void();

    }

    public WorldChunk(int xPos, int yPos){
        this(xPos, yPos, new Block[CHUNK_SIZE][CHUNK_SIZE]);
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
