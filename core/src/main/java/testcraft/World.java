package testcraft;

import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.graphics.Graphics;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static testcraft.WorldChunk.CHUNK_SIZE;

class World {

    /*
    World's name used for identifying file names and location
     */
    final String worldName;

    /*
    Fields related to the world's chunks
     */
    private List<WorldChunk> chunks;
    private ChunkLoader chunkLoader;
    private float updateCounter = 0;
    private final float updateConst = 0.5f;

    /*
    Player's character
     */
    Player player;

    World(String worldName){
        float x, y;
        try{
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("."+File.separator+worldName+File.separator+"player.dat"));
            player = (Player)stream.readObject();
        } catch (FileNotFoundException e){
            player = new Player(0f, 0f);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            player = new Player(0f, 0f);
        }
        x=player.getX() - InGameScreen.WIDTH/2/Block.PIXEL_COUNT;
        y=player.getY() - InGameScreen.HEIGHT/2/Block.PIXEL_COUNT;
        this.worldName = worldName;
        //noinspection ResultOfMethodCallIgnored
        new File("."+File.separator+worldName).mkdir();
        chunks = new LinkedList<>();
        chunkLoader = new ChunkLoader(this, chunks);
        setPos(x, y);
    }


   Rectangle getRectangle(int x, int y) {
        for (WorldChunk B : chunks) {
            if (B.chunkPosX <= x && (B.chunkPosX + CHUNK_SIZE) > x && B.chunkPosY <= y && (B.chunkPosY + CHUNK_SIZE) > y) //check if chunk contain given  coordinates
            {
                int a = x - B.chunkPosX; //block coordinates in the chunk
                int b = y - B.chunkPosY; //block coordinates in the chunk
                return B.getRectangle(a, b);
            }
        }
        System.out.println("Didn't find Rectangle.");
        return null;
    }

    boolean isBlockSolid (int x,int y)
    {
        for (WorldChunk B : chunks) {
            if(B.chunkPosX<=x && (B.chunkPosX+CHUNK_SIZE)>x && B.chunkPosY<=y && (B.chunkPosY+CHUNK_SIZE)>y) //check if chunk contain given  coordinates
            {
                int a=x-B.chunkPosX; //block coordinates in the chunk
                int b=y-B.chunkPosY; //block coordinates in the chunk
                return B.isBlockSolid(a, b);
            }
        }
        System.out.println("Didn't find the chunk. Movement");
        return false;
    }

    void setBlock (int x,int y, Block c)
    {
        for (WorldChunk B : chunks) {
            if(B.chunkPosX<=x && (B.chunkPosX+CHUNK_SIZE)>x && B.chunkPosY<=y && (B.chunkPosY+CHUNK_SIZE)>y) //check if chunk contain given  coordinates
            {
                int a=x-B.chunkPosX; //block coordinates in the chunk
                int b=y-B.chunkPosY; //block coordinates in the chunk
                 B.getRectangle(a,b);
                B.setBlock(a, b, c);
                return;
            }
        }
        System.out.println("Didn't find the chunk. setting");
    }

    Block findBlock (int x,int y) //RETURNS FOUND BLOCK
    {
        for (WorldChunk B : chunks) {
            if(B.chunkPosX<=x && (B.chunkPosX+CHUNK_SIZE)>x && B.chunkPosY<=y && (B.chunkPosY+CHUNK_SIZE)>y) //check if chunk contain given  coordinates
            {
                int a=x-B.chunkPosX; //block coordinates in the chunk
                int b=y-B.chunkPosY; //block coordinates in the chunk
               return B.setVoid(a,b);

            }
        }
        System.out.println("Didn't find the chunk. deleting");
        return  null;
    }

    /*
    Loads and unloads chunks for new X and Y coordinates
     */
    void setPos(float x, float y) {
        chunkLoader.loadChunks(x, y);
    }

    /*
    Update chunks
     */
    void update(float delta){
        updateCounter += delta;
        if(updateCounter > updateConst){
            for(WorldChunk chunk : chunks) chunk.update();
            updateCounter = 0;
        }
    }

    void saveToDisk(){
        System.out.println("Saving the world!");
        for(WorldChunk chunk : chunks){
            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("."+File.separator+worldName+File.separator+worldName+"_"+chunk.chunkPosX+"_"+chunk.chunkPosY));
                stream.writeObject(chunk);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("."+File.separator+worldName+File.separator+"player.dat"));
            stream.writeObject(player);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void render(Graphics g, float shiftX, float shiftY){
        for(WorldChunk chunk : chunks) chunk.renderChunk(g, shiftX, shiftY);
    }

}