package testcraft.chunks;

import testcraft.Block;
import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.CobblestoneBlock;
import testcraft.blocks.DirtBlock;
import testcraft.blocks.Void;

import java.io.Serializable;
import java.util.Random;

public class UndergroundChunk extends WorldChunk implements Serializable {

    private Random randy = new Random();

    public UndergroundChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        for(int i = 0; i < CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                blocks[i][j] = new CobblestoneBlock();
            }
        }

        for(int i=0; i<CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                if(randy.nextInt(15)==0){
                    createCluster(i, j, 1);
                }
                if(randy.nextInt(15)==0){
                    createCluster(i, j, 3);
                }
            }
        }
        for(int i=1; i<4; i++){
            for(int j=1; j<4; j++){
                if(randy.nextInt(2)==0){
                    makeHole(i, j);
                }
            }
        }
    }

    private void makeHole(int i, int j){
        i=i*16-8+randy.nextInt(16); j=j*16-8+randy.nextInt(16);
        createCluster(i, j, 0);
        for(int x=-1; x<2; x++){
            for(int y=-1; y<2; y++){
                if(randy.nextBoolean()){
                    createCluster(i+x, y+j, 0);
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
            case 1: return new DirtBlock();
            case 2: return new CobblestoneBlock();
            case 3: return new CoalBlock();
            default: return new Void();
        }
    }
}
