package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;
import testcraft.menus.InGameMenuScreen;

import java.util.LinkedList;

import static testcraft.TestCraftGame.multiplexer;

public class EquipmentScreen extends TestCraftScreen {


    public static int ID=6;
    private Equipment equipment;
    private  LinkedList<MyCell> l = new LinkedList<>();
    private Label chosen=new Label("",skin);
    private ScreenManager screenManager;
    private final float WINDOW_WIDTH = 500;
    private final float WINDOW_HEIGHT = 320;

    public void goBack ()
    {
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
    }

    public void toMenu ()
    {
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameMenuScreen.ID, new NullTransition(), new NullTransition());
    }

    @Override
    public void initialise(GameContainer gc) {

        super.initialise(gc);

        Window window = new Window("", skin);
        window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        window.setPosition((SCREEN_WIDTH-WINDOW_WIDTH)/2,(SCREEN_HEIGHT-WINDOW_HEIGHT)/2);

        window.add(new Label("Equipment", skin)).left().padLeft(15.0f);
        window.row();
        Table table = new Table();
        for(int y = 0; y < 4; y++) {
            for (int x = 0; x < 9; x++) {
                MyCell X= new MyCell(x+y*9);
                l.add(X);
                table.add(X).size(50.0f);
            }
            table.row();
        }
        window.add(table).pad(20.0f);
        window.row();
        chosen.setFontScale(0.5f);
        window.add(chosen);

        stage.addActor(window);
    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {
        this.screenManager=screenManager;
        equipment = ((InGameScreen) screenManager.getGameScreen(InGameScreen.ID)).getPlayer().getEquipment();
        chosen.setText(equipment.getItemName());
        if (!multiplexer.getProcessors().contains(stage, false))
            multiplexer.addProcessor(stage);
        for (MyCell m : l)
            m.update();
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
            goBack();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            toMenu();

    }
    @Override
    public void render(GameContainer gc, Graphics g) {
        screenManager.getGameScreen(InGameScreen.ID).render(gc,g);
        stage.getViewport().update(gc.getWidth(), gc.getHeight(), false);
        g.drawStage(stage);
    }


    @Override
    public int getId() {
        return ID;
    }

    class MyCell extends Table
    {
        int number;
        Label label;
        Image itemImage,bg;

        MyCell(int num)
        {
            super();
            number=num;
           addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                equipment.setChosenItem(number);
                System.out.println(equipment.available(number) ? equipment.getItem().getName() : "No item");
            }});

            itemImage= new Image();
            bg= new Image();
            label= new Label("0",skin);
            itemImage.setSize(30, 30);
            itemImage.setFillParent(false);
            itemImage.setPosition(10,10);
            label.setFontScale(0.4f);
            label.setFillParent(false);
            bg.setFillParent(true);
            //update();
           addActor(itemImage);
           addActor(label);
           addActor(bg);
        }
        void update ()
        {
            label.setVisible(true);
            itemImage.setVisible(true);
            setBackground( (number==equipment.getChosenItem()) ? skin.getDrawable("button-hover") : skin.getDrawable("cell"));
            if(!equipment.available(number))
            {
                itemImage.setVisible(false);
                label.setVisible(false);
                return;
            }
            itemImage.setDrawable( new TextureRegionDrawable(new TextureRegion(equipment.getTexture(number))));
            label.setText(String.valueOf(equipment.getQuantity(number)));
            label.setPosition(45-label.getText().length*7,-4);
        }
    }
}
