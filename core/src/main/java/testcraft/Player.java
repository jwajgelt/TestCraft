package testcraft;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.io.Serializable;

import static com.badlogic.gdx.math.MathUtils.round;
import static testcraft.Block.PIXEL_COUNT;


class Player implements Serializable {

    private  static  final float HEIGHT=PIXEL_COUNT*2-2;
    private  static  final float WIDTH=PIXEL_COUNT-2;
    public static float PLAYER_HP=100;


    private static final float PLAYER_SPEED_HORIZONTAL=2.25f;
    private static final int MAX_HORIZONTAL_MULTI=5;
    private static final int MAX_VERTICAL_MULTI=25;
    private static final float PLAYER_SPEED_VERTICAL = 1f;

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
    private Hp hp = new Hp(PLAYER_HP);

    Player(float posX, float posY){
        this.posX=posX;
        this.posY=posY;
        System.out.println(posX+" "+posY);

        speedMultiplierHorizontal=0;
        speedMultiplierVertical=0;

        grounded=true;
        equipment= new Equipment();
    }

    void moveHorizontal(float delta){
        posX+=delta;
        //System.out.println(speedMultiplierHorizontal);
    }

    void moveVertical(float delta){
        posY+=delta;
        //System.out.println(speedMultiplierVertical);
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

    void stopVertical(int a){
        speedMultiplierVertical/=5;
    }

    float getVerticalSpeed(){
        return speedMultiplierVertical*PLAYER_SPEED_VERTICAL;
    }

    boolean isVerticalSpeedCloseZero() {
        if(speedMultiplierVertical<2 && speedMultiplierVertical>-2)
            return true;
        return false;
    }

    Hp getHp() {
        return  hp;
    }


    Block getChooseBlock(){
        GameItem gameItem=equipment.getItem();
        if(gameItem instanceof  BlockItem) {
            equipment.removeItem(1);
            //System.out.println(equipment.getQuantity(equipment.getChosenItem()));
            return ((BlockItem)gameItem).getBlock();
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

    void lowerHp(float delta){
        int damage=round(delta);
        if(damage<0 || damage >0)
            System.out.println(damage);
        damage-=4;
        if(damage>0)
            hp.change(-5*damage);
    }

    boolean isGrounded(){return (grounded && speedMultiplierVertical==0);}

    void renderPlayer(Graphics graphics){
        graphics.drawSprite(playerSprite);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public boolean isReachable (Rectangle A,boolean isSolid)
    {
        float epsilon=1; //because of problems with accuracy, equals to one square pixel
        Rectangle P= new Rectangle((posX*Block.PIXEL_COUNT)-WIDTH/2, posY*PIXEL_COUNT-HEIGHT/2, WIDTH,HEIGHT);
        Rectangle Q = new Rectangle((posX-1)*Block.PIXEL_COUNT-WIDTH/2, posY*PIXEL_COUNT-HEIGHT/2, WIDTH+2*PIXEL_COUNT,HEIGHT);
        Rectangle R = new Rectangle(posX*Block.PIXEL_COUNT-WIDTH/2, (posY-1)*PIXEL_COUNT-HEIGHT/2,WIDTH,HEIGHT+2*PIXEL_COUNT);
        Rectangle[] tab = new Rectangle[3];
        for (int i =0;i<3;i++)
                tab[i]= new Rectangle(0,0,0,0);
       Intersector.intersectRectangles(A,P, tab[0]); Intersector.intersectRectangles(A,Q,tab[1]); Intersector.intersectRectangles(A,R,tab[2]);
        return ( ( (!(tab[0].area()>0)) || !isSolid)&& (tab[1].area()>epsilon || tab[2].area()>epsilon ));
    }
    void wypiszLewyDolnyRog()
    {
        System.out.println("POZYCJA: "+((posX*Block.PIXEL_COUNT)-WIDTH/2)+" "+(posY*PIXEL_COUNT-HEIGHT/2));
    }
    void wypisz_speed ()
    {
        System.out.println(getVerticalSpeed()+" "+getHorizontalSpeed());
    }
}