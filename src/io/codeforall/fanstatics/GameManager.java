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

public class GameManager implements MouseHandler, KeyboardHandler {

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
        this.keyboardInit();

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

            // background.moveTo(backgroundTargetPos);

            System.out.println(this.player.getWDir());

            // collisionManager.checkCollisions();

            Thread.sleep(800);

        }
    }

    public void mouseInit() {
        this.mouse = new Mouse(this);

        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    public void keyboardInit() {
        this.keyboard = new Keyboard(this);

        KeyboardEvent moveWPress = new KeyboardEvent();
        moveWPress.setKey(KEY_W);
        moveWPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveWRelease = new KeyboardEvent();
        moveWRelease.setKey(KEY_W);
        moveWRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent moveSPress = new KeyboardEvent();
        moveSPress.setKey(KEY_S);
        moveSPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveSRelease = new KeyboardEvent();
        moveSRelease.setKey(KEY_S);
        moveSRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent moveAPress = new KeyboardEvent();
        moveAPress.setKey(KEY_A);
        moveAPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveARelease = new KeyboardEvent();
        moveARelease.setKey(KEY_A);
        moveARelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent moveDPress = new KeyboardEvent();
        moveDPress.setKey(KEY_D);
        moveDPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent moveDRelease = new KeyboardEvent();
        moveDRelease.setKey(KEY_D);
        moveDRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(moveWPress);
        keyboard.addEventListener(moveWRelease);
        keyboard.addEventListener(moveSPress);
        keyboard.addEventListener(moveSRelease);
        keyboard.addEventListener(moveAPress);
        keyboard.addEventListener(moveARelease);
        keyboard.addEventListener(moveDPress);
        keyboard.addEventListener(moveDRelease);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // DEBUG
        System.out.println("MOUSE CLICKED");
        System.out.println("MOUSE CLICK X: " + mouseEvent.getX() +
                "MOUSE CLICK Y: " + mouseEvent.getY());
        // CHECK IF PLAYER CLICKED INSIDE THE MAP
        if (!collisionManager.isInside((int) mouseEvent.getX(), (int) mouseEvent.getY())) {
            return;
        }
        // ATTRIBUTE VECTOR COORDINATES FROM PLAYER TO MOUSE CLICK
        int directionX = (int) mouseEvent.getX() - this.player.getPosition()[0];
        int directionY = (int) mouseEvent.getY() - this.player.getPosition()[1];
        // CHANGE BACKGROUND TARGET POSITION ACCORDING TO THE INVERTED VECTOR
        this.backgroundTargetPos = new int[]{
                this.background.getSprite().getX() - directionX,
                this.background.getSprite().getY() - directionY
        };
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        this.player.setDir(
                keyboardEvent.getKey() == KEY_W,
                keyboardEvent.getKey() == KEY_S,
                keyboardEvent.getKey() == KEY_A,
                keyboardEvent.getKey() == KEY_D
        );
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        this.player.setDir(
                !(keyboardEvent.getKey() == KEY_W),
                !(keyboardEvent.getKey() == KEY_S),
                !(keyboardEvent.getKey() == KEY_A),
                !(keyboardEvent.getKey() == KEY_D)
        );
    }
}
