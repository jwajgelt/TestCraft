package testcraft;

// Handles loading and unloading of chunks for the World class

import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import testcraft.chunks.SkyChunk;
import testcraft.chunks.SurfaceChunk;
import testcraft.chunks.UndergroundChunk;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static testcraft.WorldChunk.CHUNK_SIZE;

public class ChunkLoader {

    /*
    Height and width of the loaded rectangle of chunks
     */
    private static final int LOADED_CHUNKS_X = 3;
    private static final int LOADED_CHUNKS_Y = 3;

    public final World world;
    public final List<WorldChunk> chunks;

    /*
    Holds chunks ready to be removed
     */
    private final List<WorldChunk> forRemoval = new LinkedList<>();

    ChunkLoader(World world, List<WorldChunk> chunks){
        this.chunks = chunks;
        this.world = world;
    }

    void loadChunks(float x, float y){
        int centerX = (int) Math.floor((x + CHUNK_SIZE / 2) / CHUNK_SIZE);
        int centerY = (int) Math.floor((y + CHUNK_SIZE / 2) / CHUNK_SIZE);
        int distanceX = (LOADED_CHUNKS_X - 1) / 2;
        int distanceY = (LOADED_CHUNKS_Y - 1) / 2;
        boolean[][] loaded;
        loaded = new boolean[LOADED_CHUNKS_X][LOADED_CHUNKS_Y];
        for (WorldChunk chunk : chunks) {
            int relativeX = chunk.chunkPosX / CHUNK_SIZE - centerX;
            int relativeY = chunk.chunkPosY / CHUNK_SIZE - centerY;
            //some trickery to check chunks coordinates relative to the player
            if (-distanceX <= relativeX && relativeX <= distanceX
                    && -distanceY <= relativeY && relativeY <= distanceY) {
                loaded[relativeX + distanceX][relativeY + distanceY] = true;    //if the chunk is close enough, remember chunk as loaded
            }
            else saveChunk(chunk);                                              //else, try to save the chunk to disk
        }
        for (WorldChunk chunk : forRemoval) chunks.remove(chunk);
        for (int i = 0; i < loaded.length; i++)
            for (int j = 0; j < loaded[i].length; j++) {
                if (!loaded[i][j]) {
                    int chunkPosX = (centerX - distanceX + i) * CHUNK_SIZE;
                    int chunkPosY = (centerY - distanceY + j) * CHUNK_SIZE;
                    chunks.add(loadChunk(chunkPosX, chunkPosY));
                }
            }

    }

    /*
    Loading chunks from disk, generating missing chunks
     */

    private WorldChunk loadChunk(int chunkX, int chunkY){
        try{
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream("." + File.separator + world.worldName + File.separator + world.worldName + "_" + chunkX + "_" + chunkY));
            System.out.println("RESTORING CHUNK (" + chunkX/CHUNK_SIZE + ", " + chunkY/CHUNK_SIZE + ")");
            return myReadMethod(stream);
        } catch (FileNotFoundException e){
            System.out.println("FAILED");
            System.out.println("GENERATING CHUNK (" + chunkX/CHUNK_SIZE + ", " + chunkY/CHUNK_SIZE + ")");
            if(chunkY/CHUNK_SIZE == 0) return new SurfaceChunk(chunkX, chunkY, this);
            else if(chunkY/CHUNK_SIZE > 0) return new UndergroundChunk(chunkX, chunkY, this);
            else return new SkyChunk(chunkX, chunkY);
        } catch (RuntimeException e){
            System.out.println("First load problem with deserialization, if this happens somewhere later down the line, uncomment the line below");
            //e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SkyChunk(chunkX, chunkY);
    }

    /*
    Saving chunks to disk
     */

    private void saveChunk(WorldChunk chunk){
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("." + File.separator + world.worldName + File.separator + world.worldName + "_" + chunk.chunkPosX + "_" + chunk.chunkPosY));
            myWriteMethod(stream, chunk); //FASTER SERIALIZATION
            // stream.writeObject(chunk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        forRemoval.add(chunk);
        System.out.println("STORING CHUNK (" + chunk.chunkPosX / CHUNK_SIZE + ", " + chunk.chunkPosY / CHUNK_SIZE + ")");
    }

    /*
    Checks whether desired chunk has been generated
     */

    public boolean checkChunkAvailable(int chunkX, int chunkY){
        //check currently loaded chunks
        for(WorldChunk chunk : chunks){
            if(chunk.chunkPosX == chunkX && chunk.chunkPosY == chunkY) return true;
        }
        //TODO check chunks on disk?
        return false;
    }

    /*
    Gets desired chunk
     */

    public WorldChunk getChunk(int chunkX, int chunkY){
        for(WorldChunk chunk : chunks){
            if(chunk.chunkPosX == chunkX && chunk.chunkPosY == chunkY) return chunk;
        }
        return null;
    }

    /*
    Fast serialization and deserialization, courtesy of Jacek Salata
     */

    public static WorldChunk myReadMethod(InputStream stream) throws Exception //because in.readObject throws Exception
    {
        FSTObjectInput in = new FSTObjectInput(stream);
        WorldChunk result = (WorldChunk)in.readObject(WorldChunk.class);
        in.close();
        return result;
    }

    public static void myWriteMethod(OutputStream stream, WorldChunk toWrite) throws IOException //copy-pasted from tutorial: https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization
    {
        FSTObjectOutput out = new FSTObjectOutput(stream);
        out.writeObject(toWrite, WorldChunk.class);
        out.close();
    }
}
