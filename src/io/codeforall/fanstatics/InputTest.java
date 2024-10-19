package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import src.io.codeforall.fanstatics.background.Background;
import src.io.codeforall.fanstatics.entities.Player;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.KEY_R;

public class InputTest implements MouseHandler, KeyboardHandler {

    private Background background;
    private Mouse mouse;
    private Keyboard keyboard;
    private Player player;
    public InputTest(Background background) {
        this.background = background;
        this.mouse = new Mouse(this);
        this.init();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void init() {
        this.keyboard = new Keyboard(this);

        this.mouse.addEventListener(MouseEventType.MOUSE_CLICKED);

        KeyboardEvent r = new KeyboardEvent();
        r.setKey(KEY_R);
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(r);

        KeyboardEvent rRelease = new KeyboardEvent();
        rRelease.setKey(KEY_R);
        rRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(rRelease);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        System.out.println("X: " + (mouseEvent.getX() + background.getOffSetX()) + " Y: " + (mouseEvent.getY() + background.getOffSetY()));

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_R) {
            player.pressRKey();}
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_R) {
            player.releaseRKey();}
    }
}

