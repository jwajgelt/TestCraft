package testcraft.chunks;

import testcraft.WorldChunk;

import java.io.Serializable;

public class SkyChunk extends WorldChunk implements Serializable {

    public SkyChunk(int xPos, int yPos){
        super(xPos, yPos);
    }

}
