package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import com.badlogic.gdx.math.Intersector;
import java.io.Serializable;

class Player implements Serializable {

    private static final float PIXEL_COUNT = 16f;
    private  static  final float HEIGHT=PIXEL_COUNT*2-2;
    private  static  final float WIDTH=PIXEL_COUNT-2;


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

    private Equipment equipment;

    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        System.out.println(posX+" "+posY);

        speedMultiplierHorizontal=0;
        speedMultiplierVertical=0;

        grounded=true;
        chooseBlock=1;
        equipment= new Equipment();
    }

    void moveHorizontal(float delta){
        posX+=delta;
    }

    void moveVertical(float delta){
        posY+=delta;
        System.out.println(speedMultiplierVertical);
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
        if(speedMultiplierVertical<2*MAX_VERTICAL_MULTI){
            speedMultiplierVertical++;
        }
    }

    void stopVertical(){
        speedMultiplierVertical=0;
        /*while(a>0) {
            if(speedMultiplierVertical==0)
                return;
            if (speedMultiplierVertical < 0)
                speedMultiplierVertical++;
            if (speedMultiplierVertical > 0) {
                speedMultiplierVertical--;
            }
            a--;
        }*/
    }

    float getVerticalSpeed(){
        return speedMultiplierVertical*PLAYER_SPEED_VERTICAL;
    }


    Block getChooseBlock(){
        GameItem gameItem=equipment.getItem();
        if(gameItem instanceof  Block) {
            equipment.removeItem(1);
            //System.out.println(equipment.getQuantity(equipment.getChosenItemn()));
            return (Block) gameItem;
        }
        return null;
    }

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

    public Equipment getEquipment() {
        return equipment;
    }

    public boolean isReachable (Rectangle A)
    {
        float epsilon=12; //because of problems with accuracy
       // System.out.println(posX+" "+posY);
     //  System.out.println("POZYCJA: "+((posX*Block.PIXEL_COUNT)-WIDTH/2)+" "+(posY*PIXEL_COUNT-HEIGHT/2));
        Rectangle P= new Rectangle((posX*Block.PIXEL_COUNT)-WIDTH/2, posY*PIXEL_COUNT-HEIGHT/2, WIDTH,HEIGHT);
        Rectangle Q = new Rectangle((posX-1)*Block.PIXEL_COUNT-WIDTH/2, posY*PIXEL_COUNT-HEIGHT/2, WIDTH+2*PIXEL_COUNT,HEIGHT);
        Rectangle R = new Rectangle(posX*Block.PIXEL_COUNT-WIDTH/2, (posY-1)*PIXEL_COUNT-HEIGHT/2,WIDTH,HEIGHT+2*PIXEL_COUNT);
        Rectangle[] tab = new Rectangle[3];
        for (int i =0;i<3;i++)
                tab[i]= new Rectangle(0,0,0,0);
       Intersector.intersectRectangles(A,P, tab[0]); Intersector.intersectRectangles(A,Q,tab[1]); Intersector.intersectRectangles(A,R,tab[2]);
       // for (Rectangle r :tab)
      //     System.out.println(r.area());
        return (!(tab[0].area()>0) && (tab[1].area()>epsilon || tab[2].area()>epsilon ));
    }
    void wypiszLewyDolnyRog()
    {
        System.out.println("POZYCJA: "+((posX*Block.PIXEL_COUNT)-WIDTH/2)+" "+(posY*PIXEL_COUNT-HEIGHT/2));
    }
}