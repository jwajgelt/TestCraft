package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CobblestoneBlock;

import java.io.Serializable;
import java.util.Random;

public class UndergroundChunk extends WorldChunk implements Serializable {

    private Random randy = new Random();

    public UndergroundChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos, chunkLoader);
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


}
