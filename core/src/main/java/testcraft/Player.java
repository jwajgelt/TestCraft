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

    private static final float PLAYER_SPEED_HORIZONTAL=2f;
    private static final int MAX_HORIZONTAL_MULTI=5;
    private static final int MAX_VERTICAL_MULTI=15;
    private static final float PLAYER_SPEED_VERTICAL = 1.5f;


    private int chooseBlock;

    static private Sprite playerSprite;
    static{
        playerSprite=new Sprite(new Texture("Player.png"));
        playerSprite.setPosition(640-PIXEL_COUNT/2, 360-PIXEL_COUNT);
    }

    private float posX, posY;

    private boolean grounded;

    private int speedMultiplierHorizontal;
    private int speedMultiplierVertical;

    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        System.out.println(posX+" "+posY);

        speedMultiplierHorizontal=0;
        speedMultiplierVertical=0;

        grounded=true;
        chooseBlock=1;
    }

    void moveHorizontal(float delta){
        posX+=delta;
    }

    void moveVertical(float delta){
        posY+=delta;
    }

    void triggerJump(){
        speedMultiplierVertical=-MAX_VERTICAL_MULTI;
        grounded=false;
    }

    float getHorizontalSpeed(){
        return speedMultiplierHorizontal*PLAYER_SPEED_HORIZONTAL;
    }

    void increaseHorizontalSpeed(){
        if(speedMultiplierHorizontal<MAX_HORIZONTAL_MULTI)
            speedMultiplierHorizontal+=2;
    }

    void decreaseHorizontalSpeed(){
        if(speedMultiplierHorizontal>-MAX_HORIZONTAL_MULTI)
            speedMultiplierHorizontal-=2;
    }

    void stopHorizontal(int a){

        while(a>0) {
            if(speedMultiplierHorizontal==0)
                return;
            if (speedMultiplierHorizontal < 0)
                speedMultiplierHorizontal++;
            if (speedMultiplierHorizontal > 0) {
                speedMultiplierHorizontal--;
            }
            a--;
        }
    }

    void decreaseVerticalSpeed(){
        if(speedMultiplierVertical>-2*MAX_VERTICAL_MULTI){
            speedMultiplierVertical++;
        }
    }

    void stopVertical(int a){
        while(a>0) {
            if(speedMultiplierVertical==0)
                return;
            if (speedMultiplierVertical < 0)
                speedMultiplierVertical++;
            if (speedMultiplierVertical > 0) {
                speedMultiplierVertical--;
            }
            a--;
        }
    }

    float getVerticalSpeed(){
        return speedMultiplierVertical*PLAYER_SPEED_VERTICAL;
    }

    void setChooseBlock(int a){ chooseBlock=a; }

    int getChooseBlock(){ return chooseBlock; }

    float getX(){
        return posX;
    }

    float getY(){
        return posY;
    }

    void groundHim(){
        grounded=true;
    }

    boolean isGrounded(){return (grounded && speedMultiplierVertical==0);}

    void renderPlayer(Graphics graphics){
        graphics.drawSprite(playerSprite);
    }
}