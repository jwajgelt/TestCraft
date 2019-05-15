package testcraft;

import java.io.Serializable;

public class Hp implements Serializable
{
    private float maxHp;
    private float healthpoints;
    Hp()
    {
       maxHp= healthpoints=100;
    }
    Hp(float healthpoints)
    {
       maxHp=this.healthpoints=healthpoints;
    }
    Hp(float healthpoints,float maxHp)
    {
        this.maxHp=maxHp;
        this.healthpoints=healthpoints;
    }
    boolean isDead ()
    {
        return  healthpoints<=0;
    }
    boolean isAlive()
    {
        return !isDead();
    }
    void change(float delta ) //o ile hp chcemy zmienić
    {
        healthpoints+=delta;
    }
    float getHealthPoints ()
    {
        return  healthpoints;
    }
}