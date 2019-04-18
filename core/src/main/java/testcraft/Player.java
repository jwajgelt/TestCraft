package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Player {

    static final float PIXEL_COUNT = 16f;

    private Sprite playerSprite;
    private float posX, posY;
    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        playerSprite= new Sprite(new Texture("Player.png"));
        System.out.println(posX+" "+posY);
    }

    public void move(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            posX-=30*delta;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            posX+=30*delta;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            posY+=30*delta;
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            posY-=30*delta;
    }

    float getX(){
        return posX;
    }

    float getY(){
        return posY;
    }

    void renderPlayer(Graphics graphics){
        playerSprite.setPosition(640-PIXEL_COUNT/2, 360-PIXEL_COUNT);
        graphics.drawSprite(playerSprite);
    }
}
