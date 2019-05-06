package testcraft;

import org.mini2Dx.core.serialization.annotation.Field;
import org.mini2Dx.core.graphics.Graphics;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import com.badlogic.gdx.math.Rectangle;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

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

    /*
    Player's character
     */
    Player player;

    private int centerX, centerY;

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
        chunks = new LinkedList<>();
        this.worldName = worldName;
        //noinspection ResultOfMethodCallIgnored
        new File("."+File.separator+worldName).mkdir();
        centerX = (int)x/ CHUNK_SIZE;
        centerY = (int)y/ CHUNK_SIZE;
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

    public WorldChunk myreadMethod(InputStream stream) throws Exception //because in.raeadObject throws Exception
    {
        FSTObjectInput in = new FSTObjectInput(stream);
        WorldChunk  result = (WorldChunk)in.readObject(WorldChunk.class);
        in.close();
        return result;
    }

    public void mywriteMethod( OutputStream stream, WorldChunk  toWrite ) throws IOException //copy-pasted from tutorial: https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization
    {
        FSTObjectOutput out = new FSTObjectOutput(stream);
        out.writeObject( toWrite, WorldChunk .class );
        out.close();
    }



    /*
    Loads and unloads chunks for new X and Y coordinates
     */
    void setPos(float x, float y){
        LinkedList<WorldChunk> forRemoval = new LinkedList<>();
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
                    ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("."+File.separator+worldName+File.separator+worldName+"_"+chunk.chunkPosX+"_"+chunk.chunkPosY));
                    mywriteMethod(stream,chunk); //FASTER SERIALIZATION
                   // stream.writeObject(chunk);
                } catch (IOException e){
                    e.printStackTrace();
                }
                forRemoval.add(chunk);
                System.out.println("STORING CHUNK (" + chunk.chunkPosX/CHUNK_SIZE + ", " + chunk.chunkPosY/CHUNK_SIZE +")");
            }
        }
        for(WorldChunk chunk : forRemoval) chunks.remove(chunk);
        for(int i = 0; i < loaded.length; i++)
            for(int j = 0; j < loaded[i].length; j++){
                if(!loaded[i][j]){
                    int chunkPosX = (centerX - distanceX + i)*CHUNK_SIZE;
                    int chunkPosY = (centerY - distanceY + j)*CHUNK_SIZE;
                    try{
                        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("."+File.separator+worldName+File.separator+worldName+"_"+chunkPosX+"_"+chunkPosY));
                        System.out.println("RESTORING CHUNK (" + (centerX - distanceX + i) + ", " + (centerY - distanceY + j) +")");
                        chunks.add(myreadMethod(stream)); //FASTER SERIALIZATON
                       // chunks.add((WorldChunk)stream.readObject());
                    } catch(FileNotFoundException e){
                        System.out.println("GENERATING CHUNK (" + (centerX - distanceX + i) + ", " + (centerY - distanceY + j) +")");
                        chunks.add(new WorldChunk((centerX - distanceX + i)*CHUNK_SIZE,
                                (centerY - distanceY + j)*CHUNK_SIZE,
                                new Block[CHUNK_SIZE][CHUNK_SIZE]));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
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