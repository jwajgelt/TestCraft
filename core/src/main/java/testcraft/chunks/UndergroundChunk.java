package testcraft.chunks;

import testcraft.Block;
import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.CobblestoneBlock;
import testcraft.blocks.DirtBlock;

import java.io.Serializable;
import java.util.Random;

public class UndergroundChunk extends WorldChunk implements Serializable {

    private Random randy = new Random();

    public UndergroundChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        for(int i = 0; i < CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                blocks[i][j] = new DirtBlock();
            }
        }

        for(int i=0; i<CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                if(randy.nextInt(5)==0){
                    createCluster(i, j, 2);
                }
                if(randy.nextInt(15)==0){
                    createCluster(i, j, 3);
                }
            }
        }
    }

    private void createCluster(int i, int j, int c){
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
            case 2: return new CobblestoneBlock();
            case 3: return new CoalBlock();
            default: return new DirtBlock();
        }
    }
}
