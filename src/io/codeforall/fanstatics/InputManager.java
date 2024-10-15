package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class InputManager implements MouseHandler {

    private Mouse mouse;
    private GameManager game;


    public InputManager(GameManager game) {
        this.game = game;
        this.mouseInit();
    }

    public void mouseInit() {
        this.mouse = new Mouse(this);

        this.mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        System.out.println("MOUSE CLICKED");

        System.out.println("MOUSE CLICK X: " + mouseEvent.getX() +
                "MOUSE CLICK Y: " + mouseEvent.getY());


        if (!game.getCollisionManager().isInside((int) mouseEvent.getX(), (int) mouseEvent.getY())) {
            return;
        }

        int[] direction = new int[]{(int) mouseEvent.getX() - game.player.getPosition()[0], (int) mouseEvent.getY() - game.player.getPosition()[1]};
        game.backgroundTargetPos = new int[]{game.background.getSprite().getX() - (direction[0]), game.background.getSprite().getY() - (direction[1])};

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
