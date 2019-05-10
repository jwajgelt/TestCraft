package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.apache.commons.io.FileUtils;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.TextureRegion;
import org.mini2Dx.core.screen.ScreenManager;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;



public abstract  class  MenuScreen extends TestCraftScreen{

    public boolean back=false;
    public Texture bg;
    TextureRegion bgRegion;
    static final float BUTTONWIDTH=230;
    static final float BUTTONHEIGHT=100;


    public  void addButton ( float x,float y,float height, float width, ClickListener clickListener, String text)
    {
        TextButton button= new TextButton(text,skin,"default");
        button.setSize(width, height);
        button.setPosition(x,y);
        button.addListener(clickListener);
        stage.addActor(button);
    }

    public  void addButton ( float y, ClickListener clickListener, String text)
    {
        addButton((WIDTH-BUTTONWIDTH)/2,y,BUTTONHEIGHT,BUTTONWIDTH,clickListener,text);
    }


    abstract  public void goBack(ScreenManager screenManager,GameContainer gameContainer);



    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);
        bg = new Texture(Gdx.files.absolute("craftacular/raw/dirt.png"));
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        bgRegion = new TextureRegion(bg);
        bgRegion.setRegion(0, 0, WIDTH, HEIGHT);
    }


    @Override
    abstract public void update(GameContainer gc,  ScreenManager screenManager, float delta) ;


    @Override
    public void interpolate(GameContainer gc, float alpha) {
    }

    @Override
    public void render(GameContainer gc, Graphics g) {

        super.render(gc,g);
        g.drawTextureRegion(bgRegion,0,0);
        g.drawStage(stage);
    }

    @Override
    abstract public int getId();

     class SaveLoadButton extends TextButton
    {
        final int a;
        final boolean save;
        SaveLoadButton(float x,float y,float height, float width,  Skin skin, boolean e, int b)
        {
            super("", skin);
            setHeight(height);
            setWidth(width);
            setX(x);
            setY(y);
            a=b;
            save=e;
            update();
            super.addListener( new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    File dest = new File("Default");
                    File source =new File("Saves/"+a);

                    if(save)
                    {
                        source = new File("Default");
                        dest =new File("Saves/"+a);
                    }

                    try {
                        FileUtils.deleteQuietly(dest);
                        FileUtils.forceMkdir(dest);
                        FileUtils.copyDirectory(source, dest);
                        dest.setLastModified(System.currentTimeMillis());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(save)
                        update();
                    else
                        back=true;
                }
            });

        }
        void update () {
            File file = new File("Saves/" + a);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(file.lastModified());
            //System.out.println(cal.getTime());
            super.setText("SLOT " + a + "\n " + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + ":" + String.format("%02d", cal.get(Calendar.SECOND)) + "\n" + String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)) + "." + String.format("%02d",(cal.get(Calendar.MONTH) + 1)));

          //  System.out.println("SLOT " + a + "\n " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "\n" + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1));
        }

    }



}