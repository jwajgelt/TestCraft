package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.io.Serializable;

class Player implements Serializable {

    private static final float PIXEL_COUNT = 16f;

    //public CollisionBox collisionBox;

    private boolean jumping;
    private boolean grounded;
    private int jumpTime;

    private int chooseBlock;

    static private Sprite playerSprite;

    static{
        playerSprite= new Sprite(new Texture("Player.png"));
        playerSprite.setPosition(640-PIXEL_COUNT/2, 360-PIXEL_COUNT);
    }

    private float posX, posY;
    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        //collisionBox=new CollisionBox(posX, posY, PIXEL_COUNT-1, (PIXEL_COUNT-1)*2);
        System.out.println(posX+" "+posY);

        jumping=false;
        grounded=true;
        jumpTime=0;
        chooseBlock=1;
    }

    void moveHorizontal(float delta){
        posX+=delta;
    }

    void fall(float delta){
        posY+=delta;
    }

    void jump(float delta){
        jumpTime++;
        if(jumpTime>15) //changed that form 10 to 15 to make player able to jump on 2 blocks obstacle
        {
            jumping=false;
        }
        posY-=delta;
    }

    void stopJump(){
        jumping=false;
    }

    void groundHim(){
        grounded=true;
    }

    void triggerJump(){
        jumping=true;
        grounded=false;
        jumpTime=0;
    }

    void setChooseBlock(int a){ chooseBlock=a; }

    int getChooseBlock(){ return chooseBlock; }

    float getX(){
        return posX;
    }

    float getY(){
        return posY;
    }

    boolean isJumping(){ return jumping; }

    boolean isGrounded(){ return grounded; }

    void renderPlayer(Graphics graphics){
        graphics.drawSprite(playerSprite);
    }
}