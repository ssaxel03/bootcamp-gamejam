package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
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

    private Rectangle screen;

    Player player;
    ArrayList<Enemy> enemies;
    Background background;
    ArrayList<Collideable> collideables;

    public GameManager() {
        this.keyboardInit();
        this.screen = new Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.enemies = new ArrayList<>();
        collideables = new ArrayList<>();
        collideables.addAll(enemies);
        collideables.add(player);

    }

    public void start() {
        this.background = new Background(SCREEN_WIDTH, SCREEN_HEIGHT, enemies);
        this.player = new Player();
        enemies.add(new Enemy(10, 10));

        try{
            this.gameLoop();
        } catch(Exception e) {
            System.out.println("Waiting");
        }


    }

    public void gameLoop() throws InterruptedException {
        while(true) {

            Thread.sleep(20);
            for (Enemy enemy : enemies) {
                enemy.move(player);
            }

        }
    }

    public void keyboardInit() {

        this.keyboard = new Keyboard(this);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KEY_W);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_S);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KEY_A);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KEY_D);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(moveUp);
        keyboard.addEventListener(moveDown);
        keyboard.addEventListener(moveLeft);
        keyboard.addEventListener(moveRight);

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
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
