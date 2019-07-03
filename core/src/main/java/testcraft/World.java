package testcraft;

import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.graphics.Graphics;

import java.io.*;
import java.util.Arrays;
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
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("."+File.separator+"Saves"+File.separator+ worldName+File.separator+"player.dat"));
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
        chunks = new LinkedList<>();
        chunkLoader = new ChunkLoader(this, chunks);
        setPos(x, y);
    }

    List<Integer> coordinates (int x, int y) //returns list of three numbers: (chunk number, first coordinte in this chunk, second one)
    {
        for (int i=0;i<chunks.size();i++)
        {
            WorldChunk B=chunks.get(i);
            if (B.chunkPosX <= x && (B.chunkPosX + CHUNK_SIZE) > x && B.chunkPosY <= y && (B.chunkPosY + CHUNK_SIZE) > y) //check if chunk contain given  coordinates
            {
                int a = x - B.chunkPosX; //block coordinates in the chunk
                int b = y - B.chunkPosY; //block coordinates in the chunk
                return  Arrays.asList(i,a,b);
            }
        }
        return null;
    }

   Rectangle getRectangle(int x, int y) {
        List<Integer> Q=coordinates(x,y);
        return chunks.get(Q.get(0)).getRectangle(Q.get(1), Q.get(2));
    }

    boolean isBlockSolid (int x,int y)
    {
        return findBlock(x,y).isSolid();
    }

    boolean isBlockOccupied (int x,int y)
    {
        return findBlock(x,y).isOccupied();
    }

    void setBlock (int x,int y, Block c)
    {
        List<Integer> Q=coordinates(x,y);
        if(Q == null) return;
        chunks.get(Q.get(0)).setBlock(Q.get(1), Q.get(2),c);
    }

    Block findBlock (int x,int y) //RETURNS FOUND BLOCK
    {
        List<Integer> Q=coordinates(x,y);
        if(Q == null) return null;
        Block result = chunks.get(Q.get(0)).getBlock(Q.get(1), Q.get(2));
        return result;
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
            for(WorldChunk chunk : chunks) chunk.update(chunkLoader);
            updateCounter = 0;
        }
    }

    void saveToDisk(){
        System.out.println("Saving the world!");
        for(WorldChunk chunk : chunks){
                chunkLoader.saveChunk(chunk);
        }
        try{
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("."+File.separator+"Saves"+File.separator+ worldName+File.separator+"player.dat"));
            stream.writeObject(player);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void render(Graphics g, float shiftX, float shiftY){
        for(WorldChunk chunk : chunks) chunk.renderChunk(g, shiftX, shiftY);
    }

}