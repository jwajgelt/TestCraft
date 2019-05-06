package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.*;

import java.io.Serializable;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SurfaceChunk extends WorldChunk implements Serializable {

    private final int[] surfaceLevel = new int[CHUNK_SIZE];

    public SurfaceChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        surfaceLevel[0] = -1;
        //if the chunk to the left is already generated, continue at the same height
        Random randy = new Random();
        if(chunkLoader.checkChunkAvailable(xPos - CHUNK_SIZE, yPos)){
            WorldChunk leftChunk = chunkLoader.getChunk(xPos - CHUNK_SIZE, yPos);
            if(leftChunk instanceof SurfaceChunk){
                surfaceLevel[0] = max(1, min(CHUNK_SIZE-2, ((SurfaceChunk)leftChunk).surfaceLevel[CHUNK_SIZE-1] + (randy.nextInt(4)+1)/2-1));
                for(int i = 1; i < CHUNK_SIZE; i++){
                    surfaceLevel[i] = max(1, min(CHUNK_SIZE-2, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        //same if the chunk to the right is already generated
        if(chunkLoader.checkChunkAvailable(xPos + CHUNK_SIZE, yPos)){
            WorldChunk rightChunk = chunkLoader.getChunk(xPos + CHUNK_SIZE, yPos);
            if(rightChunk instanceof SurfaceChunk){
                surfaceLevel[CHUNK_SIZE-1] = max(1, min(CHUNK_SIZE-2, ((SurfaceChunk)rightChunk).surfaceLevel[0] + (randy.nextInt(4)+1)/2-1));
                for(int i = CHUNK_SIZE-2; i >= 0; i--){
                    surfaceLevel[i] = max(1, min(CHUNK_SIZE-2, surfaceLevel[i+1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        //if neither are, generate whatever
        if(surfaceLevel[0] == -1){
            surfaceLevel[0] = randy.nextInt(CHUNK_SIZE/16)+1;
            for(int i = 1; i < CHUNK_SIZE; i++){
                surfaceLevel[i] = max(1, min(CHUNK_SIZE-2, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
            }
        }
        for(int i = 0; i < CHUNK_SIZE; i++){
            //top level should be grassy dirt
            blocks[i][surfaceLevel[i]] = new GrassDirtBlock();
            //throw some grass blocks on top
            if(randy.nextInt(5) == 0) blocks[i][surfaceLevel[i]-1] = new GrassBlock();
            for(int j = surfaceLevel[i]+1; j < CHUNK_SIZE; j++){
                blocks[i][j] = (j < surfaceLevel[i]+2) ? new DirtBlock() : (randy.nextBoolean()) ? (randy.nextBoolean() ? new DirtBlock() : new CoalBlock()) : randy.nextBoolean() ? new DirtBlock() : new CobblestoneBlock(randy.nextInt(10));
            }
        }
    }
}
