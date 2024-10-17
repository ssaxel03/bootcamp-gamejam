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

public class GameManager implements KeyboardHandler {

    public final static int SCREEN_WIDTH = 1920;
    public final static int SCREEN_HEIGHT = 1080;
    private Keyboard keyboard;
    private Rectangle screen;
    private CollisionManager collisionManager;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bulletsShot;
    private Background background;

    public GameManager() {
        // INITIALIZE IO RECEIVERS
        this.keyboardInit();
        // CREATE EMPTY SCREEN BEHIND BACKGROUND
        this.screen = new Rectangle(0, 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2);
        this.screen.setColor(Color.BLACK);
        this.screen.fill();
        // CREATE EMPTY LIST OF ENEMIES
        this.enemies = new ArrayList<>();
        this.bulletsShot = new ArrayList<>();
        // CREATE BACKGROUND
        this.background = new Background(SCREEN_WIDTH, SCREEN_HEIGHT, enemies);
    }

    public void play() {

        this.player = new Player(bulletsShot);
        this.background.setPlayer(this.player);
        enemies.add(new Enemy(10, 10));

        this.collisionManager = new CollisionManager(background, player, enemies, bulletsShot);

        try {
            this.gameLoop();
        } catch (InterruptedException e) {
            System.out.println("Waiting...");
        }
    }

    public void gameLoop() throws InterruptedException {
        while (true) {

            this.background.moveTo();

            this.player.shoot(enemies);

            collisionManager.checkCollisions();

            System.out.println("Enemy is " + enemies.get(0).getHealth() + " HP");
            System.out.println("BULLETS LIST HAS " + bulletsShot.size() + " BULLETS");

            Thread.sleep(10);

        }
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
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_W) {this.player.setWDir(1);}
        if(keyboardEvent.getKey() == KEY_S) {this.player.setSDir(1);}
        if(keyboardEvent.getKey() == KEY_A) {this.player.setADir(1);}
        if(keyboardEvent.getKey() == KEY_D) {this.player.setDDir(1);}

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_W) {this.player.setWDir(0);}
        if(keyboardEvent.getKey() == KEY_S) {this.player.setSDir(0);}
        if(keyboardEvent.getKey() == KEY_A) {this.player.setADir(0);}
        if(keyboardEvent.getKey() == KEY_D) {this.player.setDDir(0);}
    }
}
