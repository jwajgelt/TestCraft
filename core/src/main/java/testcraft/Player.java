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

    private static final float PLAYER_SPEED=0.3f;
    private static final int MAX_MULTI=30;


    private int chooseBlock;

    static private Sprite playerSprite;
    static{
        playerSprite=new Sprite(new Texture("Player.png"));
        playerSprite.setPosition(640-PIXEL_COUNT/2, 360-PIXEL_COUNT);
    }

    private float posX, posY;

    private boolean jumping;
    private boolean grounded;
    private int jumpTime;

    private int speedMultiplierHorizontal;
    private int speedMultiplierVertical;




    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        System.out.println(posX+" "+posY);

        speedMultiplierHorizontal=0;
        speedMultiplierVertical=0;

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
        if(speedMultiplierVertical<2*MAX_MULTI){
            speedMultiplierVertical++;
        }
        grounded=false;
    }

    void jump(float delta){
        jumpTime++;
        if(jumpTime==9 || jumpTime==12 || jumpTime==15){
            speedMultiplierVertical--;
        }
        if(jumpTime>15) //changed that form 10 to 15 to make player able to jump on 2 blocks obstacle
        {
            jumping=false;
        }
        posY-=delta;
    }

    void stopJump(){
        jumping=false;
        speedMultiplierVertical=0;
    }

    void groundHim(){
        grounded=true;
        speedMultiplierVertical=0;
    }

    void triggerJump(){
        jumping=true;
        grounded=false;
        speedMultiplierVertical=MAX_MULTI;
        jumpTime=0;
    }

    float getHorizontalSpeed(){
        return speedMultiplierHorizontal*PLAYER_SPEED;
    }

    void increaseHorizontalSpeed(){
        if(speedMultiplierHorizontal<MAX_MULTI)
            speedMultiplierHorizontal+=2;
    }

    void deacreseHorizontalSpeed(){
        if(speedMultiplierHorizontal>-MAX_MULTI)
            speedMultiplierHorizontal-=2;
    }

    void stopHorizontal(int a){
        while(a>0) {
            if (speedMultiplierHorizontal < 0)
                speedMultiplierHorizontal++;
            if (speedMultiplierHorizontal > 0) {
                speedMultiplierHorizontal--;
            }
            a--;
        }
    }

    float getVerticalSpeed(){
        return speedMultiplierVertical*PLAYER_SPEED;
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