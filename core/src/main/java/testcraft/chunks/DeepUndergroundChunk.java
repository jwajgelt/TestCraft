package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CobblestoneBlock;

import java.io.Serializable;
import java.util.Random;

public class DeepUndergroundChunk extends WorldChunk implements Serializable {
    private Random random=new Random();

    public DeepUndergroundChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        //fill with stone
        for(int i = 0; i < CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                blocks[i][j] = new CobblestoneBlock();
            }
        }
        //clusters
        for(int i=0; i<CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                if(random.nextInt(20)==0){
                    createCluster(i, j, 1);
                }
                if(random.nextInt(20)==0){
                    createCluster(i, j, 3);
                }
                if(random.nextInt(35)==0){
                    createCluster(i, j, 10);
                }
            }
        }

    }
}
