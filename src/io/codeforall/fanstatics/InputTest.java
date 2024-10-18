package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import src.io.codeforall.fanstatics.background.Background;

public class InputTest implements MouseHandler {

    private Background background;
    private Mouse mouse;
    public InputTest(Background background) {
        this.background = background;
        this.mouse = new Mouse(this);
        this.init();
    }

    public void init() {
        this.mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        System.out.println("X: " + (mouseEvent.getX() + background.getOffSetX()) + " Y: " + (mouseEvent.getY() + background.getOffSetY()));

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
