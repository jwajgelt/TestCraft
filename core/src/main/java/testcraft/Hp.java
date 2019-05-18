package testcraft;

import java.io.Serializable;

public class Hp implements Serializable
{
    private float maxHp;
    private float healthPoints;

    Hp() {
        maxHp= healthPoints =100;
    }

    Hp(float healthPoints) {
        maxHp=this.healthPoints = healthPoints;
    }

    Hp(float healthPoints, float maxHp) {
        this.maxHp=maxHp;
        this.healthPoints = healthPoints;
    }

    boolean isDead ()
    {
        return  healthPoints <=0;
    }

    boolean isAlive()
    {
        return !isDead();
    }

    //o ile hp chcemy zmienić
    void change(float delta ) {
        healthPoints +=delta;
    }

    float getHealthPoints ()
    {
        return healthPoints;
    }
}