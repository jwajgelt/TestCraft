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
                surfaceLevel[0] = max(6, min(CHUNK_SIZE-7, ((SurfaceChunk)leftChunk).surfaceLevel[CHUNK_SIZE-1] + (randy.nextInt(4)+1)/2-1));
                for(int i = 1; i < CHUNK_SIZE; i++){
                    surfaceLevel[i] = max(6, min(CHUNK_SIZE-7, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        //same if the chunk to the right is already generated
        if(chunkLoader.checkChunkAvailable(xPos + CHUNK_SIZE, yPos)){
            WorldChunk rightChunk = chunkLoader.getChunk(xPos + CHUNK_SIZE, yPos);
            if(rightChunk instanceof SurfaceChunk){
                surfaceLevel[CHUNK_SIZE-1] = max(6, min(CHUNK_SIZE-7, ((SurfaceChunk)rightChunk).surfaceLevel[0] + (randy.nextInt(4)+1)/2-1));
                for(int i = CHUNK_SIZE-2; i >= 0; i--){
                    surfaceLevel[i] = max(6, min(CHUNK_SIZE-7, surfaceLevel[i+1] + (randy.nextInt(4)+1)/2-1));
                }
            }
        }
        //if neither are, generate whatever
        if(surfaceLevel[0] == -1){
            surfaceLevel[0] = randy.nextInt(CHUNK_SIZE/16)+6;
            for(int i = 1; i < CHUNK_SIZE; i++){
                surfaceLevel[i] = max(6, min(CHUNK_SIZE-7, surfaceLevel[i-1] + (randy.nextInt(4)+1)/2-1));
            }
        }
        for(int i = 0; i < CHUNK_SIZE; i++){
            //top level should be grassy dirt
            blocks[i][surfaceLevel[i]] = new GrassDirtBlock();
            //throw some grass blocks on top
            if(randy.nextInt(5) == 0) blocks[i][surfaceLevel[i]-1] = new GrassBlock();
            for(int j = surfaceLevel[i]+1; j < CHUNK_SIZE; j++){
                blocks[i][j] =  new DirtBlock() ;
            }
        }
        //creating trees
        int lastTree=-2;
        for(int i=2; i<CHUNK_SIZE-2; i++){
            if(randy.nextInt(10)==0 && lastTree+4<i){
                lastTree=i;
                blocks[i][surfaceLevel[i]-1]=new LogBlock();
                blocks[i][surfaceLevel[i]-2]=new LogBlock();
                blocks[i][surfaceLevel[i]-3]=new LogBlock();
                blocks[i][surfaceLevel[i]-4]=new LogBlock();
                blocks[i-1][surfaceLevel[i]-3]=new LeavesBlock();
                blocks[i-2][surfaceLevel[i]-3]=new LeavesBlock();
                blocks[i+1][surfaceLevel[i]-3]=new LeavesBlock();
                blocks[i+2][surfaceLevel[i]-3]=new LeavesBlock();
                blocks[i-1][surfaceLevel[i]-4]=new LeavesBlock();
                blocks[i-2][surfaceLevel[i]-4]=new LeavesBlock();
                blocks[i+1][surfaceLevel[i]-4]=new LeavesBlock();
                blocks[i+2][surfaceLevel[i]-4]=new LeavesBlock();
                blocks[i-1][surfaceLevel[i]-5]=new LeavesBlock();
                blocks[i][surfaceLevel[i]-5]=new LeavesBlock();
                blocks[i+1][surfaceLevel[i]-5]=new LeavesBlock();
            }
        }

        for(int i=0; i<CHUNK_SIZE; i++){
            for(int j = surfaceLevel[i]+4; j < CHUNK_SIZE; j++){
                if(randy.nextInt(CHUNK_SIZE-j+2)==0){
                    createCluster(i, j, 2);
                }
                if(randy.nextInt(25)==0){
                    createCluster(i, j, 3);
                }
            }
        }
    }
}
