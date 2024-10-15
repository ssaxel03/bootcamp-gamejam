package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import src.io.codeforall.fanstatics.background.Background;
import src.io.codeforall.fanstatics.entities.Enemy;
import src.io.codeforall.fanstatics.entities.Player;

import javax.swing.*;
import java.util.ArrayList;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;

public class GameManager implements MouseHandler {

    public final static int SCREEN_WIDTH = 1920;
    public final static int SCREEN_HEIGHT = 1080;

    private Keyboard keyboard;
    private Mouse mouse;

    private Rectangle screen;

    private CollisionManager collisionManager;

    int[] backgroundTargetPos;

    Player player;
    ArrayList<Enemy> enemies;
    Background background;

    public GameManager() {
        this.backgroundTargetPos = new int[]{0, 0};

        this.mouseInit();

        this.screen = new Rectangle(0, 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2);
        this.screen.setColor(Color.BLACK);
        this.screen.fill();

        this.enemies = new ArrayList<>();

        this.background = new Background(SCREEN_WIDTH, SCREEN_HEIGHT, enemies);
    }

    public void play() {

        this.player = new Player();
        this.background.setPlayer(this.player);
        enemies.add(new Enemy(10, 10));

        this.backgroundTargetPos = new int[]{0, 0};

        this.collisionManager = new CollisionManager(enemies, background, player);

        try {
            this.gameLoop();
        } catch (InterruptedException e) {
            System.out.println("Waiting...");
        }
    }

    public void gameLoop() throws InterruptedException {
        while (true) {

            background.moveTo(backgroundTargetPos);

            collisionManager.checkCollisions();

            Thread.sleep(15);

        }
    }

    public void mouseInit() {
        this.mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        System.out.println("MOUSE CLICKED");

        System.out.println("MOUSE CLICK X: " + mouseEvent.getX() +
                "MOUSE CLICK Y: " + mouseEvent.getY());

        if (!collisionManager.isInside((int) mouseEvent.getX(), (int) mouseEvent.getY())) {
            return;
        }

        int[] direction = new int[]{(int) mouseEvent.getX() - this.player.getPosition()[0], (int) mouseEvent.getY() - this.player.getPosition()[1]};
        this.backgroundTargetPos = new int[]{this.background.getSprite().getX() - (direction[0]), this.background.getSprite().getY() - (direction[1])};
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
