package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.CobblestoneBlock;
import testcraft.blocks.DirtBlock;
import testcraft.blocks.GrassDirtBlock;

import java.io.Serializable;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SurfaceChunk extends WorldChunk implements Serializable {

    protected Random randy = new Random();

    final int[] surfaceLevel = new int[CHUNK_SIZE];

    public SurfaceChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        surfaceLevel[0] = -1;
        if(chunkLoader.checkChunkAvailable(xPos - CHUNK_SIZE, yPos)){
            WorldChunk leftChunk = chunkLoader.getChunk(xPos - CHUNK_SIZE, yPos);
            if(leftChunk instanceof SurfaceChunk){
                surfaceLevel[0] = ((SurfaceChunk)leftChunk).surfaceLevel[CHUNK_SIZE-1];
                for(int i = 1; i < CHUNK_SIZE; i++){
                    surfaceLevel[i] = max(0, min(CHUNK_SIZE-2, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        if(chunkLoader.checkChunkAvailable(xPos + CHUNK_SIZE, yPos)){
            WorldChunk rightChunk = chunkLoader.getChunk(xPos + CHUNK_SIZE, yPos);
            if(rightChunk instanceof SurfaceChunk){
                surfaceLevel[CHUNK_SIZE-1] = ((SurfaceChunk)rightChunk).surfaceLevel[0];
                for(int i = CHUNK_SIZE-2; i >= 0; i--){
                    surfaceLevel[i] = max(0, min(CHUNK_SIZE-2, surfaceLevel[i+1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        if(surfaceLevel[0] == -1){
            surfaceLevel[0] = randy.nextInt(CHUNK_SIZE/16);
            for(int i = 1; i < CHUNK_SIZE; i++){
                surfaceLevel[i] = max(0, min(CHUNK_SIZE-2, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
            }
        }
        for(int i = 0; i < CHUNK_SIZE; i++){
            blocks[i][surfaceLevel[i]] = new GrassDirtBlock();
            for(int j = surfaceLevel[i]+1; j < CHUNK_SIZE; j++){
                blocks[i][j] = (j < surfaceLevel[i]+2) ? new DirtBlock() : (randy.nextBoolean()) ? (randy.nextBoolean() ? new DirtBlock() : new CoalBlock()) : randy.nextBoolean() ? new DirtBlock() : new CobblestoneBlock(randy.nextInt(10));;
            }
        }
    }

}
