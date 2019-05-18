package testcraft;

import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import testcraft.blocks.*;
import testcraft.blocks.Void;

import java.io.Serializable;
import java.util.Random;

public abstract class WorldChunk implements Serializable {

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

    //I'd say unnecessary - implemented getBlock to get blocks[x][y], can then be set using setBlock to 'new Void()'    ~Wajgelt
    /*Block setVoid (int a, int b)
    {
        Block X= blocks[a][b];
        blocks[a][b]=new Void();
        return X;
    }*/

    //Function to get block from chunk by its chunk coordinates
    Block getBlock(int x, int y){
        return blocks[x][y];
    }

    void setBlock(int a, int b, Block c){
        if(c!=null)
            blocks[a][b]=c;
    }

    boolean isBlockSolid(int a, int b){
        return blocks[a][b].isSolid();
    }

    public void update(){
        //place to check some block-specific updates
        //not pretty, should figure something better out
        for(int i = 0; i < CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                Block block = blocks[i][j];
                if(block instanceof GrassDirtBlock){
                    if(j > 0){
                        if(blocks[i][j-1].isSolid()) blocks[i][j] = new DirtBlock();
                        else if(blocks[i][j-1] instanceof Void && new Random().nextInt(500) == 0) blocks[i][j-1] = new GrassBlock();
                    }
                } else if(block instanceof DirtBlock){
                    if(checkGrass(i-1, j) || checkGrass(i+1, j)){
                        if(j > 0 && !blocks[i][j-1].isSolid() && new Random().nextInt(20)==0) blocks[i][j] = new GrassDirtBlock();
                    }
                } else if(block instanceof GrassBlock){
                    if(j < CHUNK_SIZE-1 && !blocks[i][j+1].isSolid()) blocks[i][j] = new Void();
                }
            }
        }
    }

    private boolean checkGrass(int i, int j){
        if(i<0 || i >=CHUNK_SIZE)
            return false;
        if(j<1 || j >=CHUNK_SIZE-1)
            return false;
        return blocks[i][j] instanceof GrassDirtBlock || blocks[i][j - 1] instanceof GrassDirtBlock || blocks[i][j + 1] instanceof GrassDirtBlock;
    }

    protected void createCluster(int i, int j, int c){
        if(i>0)
            blocks[i-1][j]=pickBlock(c);
        if(i<CHUNK_SIZE-1)
            blocks[i+1][j]=pickBlock(c);
        blocks[i][j]=pickBlock(c);
        if(j>0)
            blocks[i][j-1]=pickBlock(c);
        if(j<CHUNK_SIZE-1)
            blocks[i][j+1]=pickBlock(c);
    }

    private Block pickBlock(int c){
        switch (c){
            case 1: return new DirtBlock();
            case 2: return new CobblestoneBlock();
            case 3: return new CoalBlock();
            default: return new Void();
        }
    }

}
