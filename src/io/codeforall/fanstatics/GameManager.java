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

import java.util.ArrayList;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;

public class GameManager implements KeyboardHandler, MouseHandler {

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
        this.backgroundTargetPos = new int[] {0, 0};

        this.mouseInit();
        this.keyboardInit();

        this.screen = new Rectangle(0, 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2);
        this.screen.setColor(Color.BLACK);
        this.screen.fill();

        this.enemies = new ArrayList<>();

        this.background = new Background(SCREEN_WIDTH, SCREEN_HEIGHT, enemies);
    }

    public void start() {
        this.player = new Player();
        this.background.setPlayer(this.player);
        enemies.add(new Enemy(10, 10));

        this.backgroundTargetPos = new int[] {0, 0};

        this.collisionManager = new CollisionManager(enemies, background, player);

        try{
            this.gameLoop();
        } catch(InterruptedException e) {
            System.out.println("Waiting...");
        }


    }

    public void gameLoop() throws InterruptedException {
        while(true) {

            Thread.sleep(15);


            for (Enemy enemy : enemies) {
                enemy.move(player);
            }

            background.moveTo(backgroundTargetPos);

            collisionManager.checkCollisions();

        }
    }

    public void keyboardInit() {

        this.keyboard = new Keyboard(this);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KEY_W);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(moveUp);
    }

    public void mouseInit() {
        this.mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch(keyboardEvent.getKey()) {
            case KEY_W -> this.playerMove(Directions.UP);
            case KEY_S -> this.playerMove(Directions.DOWN);
            case KEY_A -> this.playerMove(Directions.LEFT);
            case KEY_D -> this.playerMove(Directions.RIGHT);
        }
    }

    public void playerMove(Directions direction) {

        int[] translate = {0, 0};
        int[] newOffset = {this.background.getOffset()[0], this.background.getOffset()[1]};

        switch(direction) {
            case UP -> newOffset = new int[] {this.background.getOffset()[0], this.background.getOffset()[1] + 50};
            case DOWN -> newOffset = new int[] {this.background.getOffset()[0], this.background.getOffset()[1] - 50};
            case LEFT -> newOffset = new int[] {this.background.getOffset()[0] + 50, this.background.getOffset()[1]};
            case RIGHT -> newOffset = new int[] {this.background.getOffset()[0] - 50, this.background.getOffset()[1]};
        }

        translate = new int[] {(newOffset[0] - this.background.getOffset()[0]) * this.player.getSpeed(),
                (newOffset[1] - this.background.getOffset()[1]) * this.player.getSpeed()};

        this.background.move(translate);
        this.background.setOffset(newOffset);
        this.background.getBoxCollider().move(translate);

        System.out.println(this.background.getBoxCollider().bounds.getX() + " " +
                this.background.getBoxCollider().bounds.getY());
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {



        int[] clickPosition = new int []{(int) mouseEvent.getX(), (int) mouseEvent.getY()};
        System.out.println("MOUSE CLICK X: " + clickPosition[0] +
                "MOUSE CLICK Y: " + clickPosition[1]);

        if(!collisionManager.isInside(clickPosition)) {
            return;
        }

        int[] direction = new int[] {clickPosition[0] - this.player.getPosition()[0], clickPosition[1] - this.player.getPosition()[1]};
        this.backgroundTargetPos = new int[] {this.background.getSprite().getX() - (direction[0]), this.background.getSprite().getY() - (direction[1])};
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
