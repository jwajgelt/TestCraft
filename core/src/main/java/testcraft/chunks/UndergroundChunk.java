package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;
import testcraft.blocks.CoalBlock;
import testcraft.blocks.CobblestoneBlock;
import testcraft.blocks.DirtBlock;

import java.io.Serializable;
import java.util.Random;

public class UndergroundChunk extends WorldChunk implements Serializable {

    Random randy = new Random();

    public UndergroundChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos);
        for(int i = 0; i < CHUNK_SIZE; i++){
            for(int j = 0; j < CHUNK_SIZE; j++){
                blocks[i][j] = (randy.nextBoolean()) ? (randy.nextBoolean() ? new DirtBlock() : new CoalBlock()) : randy.nextBoolean() ? new DirtBlock() : new CobblestoneBlock(randy.nextInt(10));
            }
        }
    }
}
