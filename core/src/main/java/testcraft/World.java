package testcraft;

import org.mini2Dx.core.serialization.SerializationException;
import org.mini2Dx.core.serialization.annotation.Field;
import org.mini2Dx.core.graphics.Graphics;

import java.util.LinkedList;

import static testcraft.WorldChunk.CHUNK_SIZE;
import static testcraft.Block.PIXEL_COUNT;

class World {

    /*
    Height and width of the loaded rectangle of chunks
     */
    private final int LOADED_CHUNKS_X = 3;
    private final int LOADED_CHUNKS_Y = 3;

    /*
    World's name used for identifying file names and location
     */
    @Field private final String worldName;

    private LinkedList<WorldChunk> chunks;

    private int centerX, centerY, chunkCount;

    World(String worldName, int x, int y){
        chunks = new LinkedList<WorldChunk>();
        this.worldName = worldName;
        centerX = x/ CHUNK_SIZE;
        centerY = y/ CHUNK_SIZE;
        chunkCount = 0;
        setPos(x, y);
    }

    public  void findBlock (int x,int y) //finds
    {
        for (WorldChunk B : chunks) {
            if(B.chunkPosX<=x && (B.chunkPosX+CHUNK_SIZE)>x && B.chunkPosY<=y && (B.chunkPosY+CHUNK_SIZE)>y) //check if chunk contain given  coordinates
            {
                int a=x-B.chunkPosX; //block coordinates in the chunk
                int b=y-B.chunkPosY; //block coordinates in the chunk
                B.setVoid(a,b);
                return;
            }
        }
        System.out.println("Didn't find the chunk. Something went wrong!");
        return;
    }



    /*
    Loads and unloads chunks for new X and Y coordinates
     */
    void setPos(float x, float y){
        LinkedList<WorldChunk> forRemoval = new LinkedList<WorldChunk>();
        centerX = (int)java.lang.Math.floor((x+CHUNK_SIZE/2)/CHUNK_SIZE);
        centerY = (int)java.lang.Math.floor((y+CHUNK_SIZE/2)/CHUNK_SIZE);
        int distanceX = (LOADED_CHUNKS_X-1)/2;
        int distanceY = (LOADED_CHUNKS_Y-1)/2;
        boolean[][] loaded;
        loaded = new boolean[LOADED_CHUNKS_X][LOADED_CHUNKS_Y];
        for(WorldChunk chunk : chunks){
            int relativeX = chunk.chunkPosX/CHUNK_SIZE - centerX;
            int relativeY = chunk.chunkPosY/CHUNK_SIZE - centerY;
            if(-distanceX <= relativeX && relativeX <= distanceX
            && -distanceY <= relativeY && relativeY <= distanceY){
                loaded[relativeX + distanceX][relativeY + distanceY] = true;
            }
            else{
                try {
                    System.out.println(chunk.getJson());
                } catch (SerializationException e) {
                    e.printStackTrace();
                }
                forRemoval.add(chunk);
                System.out.println("REMOVING CHUNK (" + chunk.chunkPosX/CHUNK_SIZE + ", " + chunk.chunkPosY/CHUNK_SIZE +")");
                chunkCount--;
            }
        }
        for(WorldChunk chunk : forRemoval) chunks.remove(chunk);
        for(int i = 0; i < loaded.length; i++)
            for(int j = 0; j < loaded[i].length; j++){
                if(!loaded[i][j]){
                    System.out.println("GENERATING CHUNK (" + (centerX - distanceX + i) + ", " + (centerY - distanceY + j) +")");
                    chunkCount++;
                    chunks.add(new WorldChunk((centerX - distanceX + i)*CHUNK_SIZE,
                            (centerY - distanceY + j)*CHUNK_SIZE,
                            new Block[64][64]));
                }
            }
    }

    void render(Graphics g, float shiftX, float shiftY){
        for(WorldChunk chunk : chunks) chunk.renderChunk(g, shiftX, shiftY);
    }

}
