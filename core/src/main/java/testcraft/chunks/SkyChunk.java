package testcraft.chunks;

import testcraft.ChunkLoader;
import testcraft.WorldChunk;

import java.io.Serializable;

public class SkyChunk extends WorldChunk implements Serializable {

    public SkyChunk(int xPos, int yPos, ChunkLoader chunkLoader){
        super(xPos, yPos, chunkLoader);
    }

}
