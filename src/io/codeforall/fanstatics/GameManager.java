package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
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
import java.awt.*;
import java.util.ArrayList;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;

public class GameManager implements KeyboardHandler {

    public final static int SCREEN_WIDTH = 2340;
    public final static int SCREEN_HEIGHT = 2220;
    private Keyboard keyboard;
    private Rectangle screen;
    private CollisionManager collisionManager;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bulletsShot;
    private ArrayList<Interactable> interactables;
    private Background background;
    private HUD hud;
    private InputTest inputTest;
    private int currentRoom;
    private long spawnTimer;
    private final long spawnDelay = 1000;

    public GameManager() {
        // INITIALIZE IO RECEIVERS
        this.keyboardInit();
        // CREATE EMPTY SCREEN BEHIND BACKGROUND
        this.screen = new Rectangle(0, 0, SCREEN_WIDTH * 3, SCREEN_HEIGHT * 3);
        this.screen.setColor(Color.BLACK);
        this.screen.fill();
        // CREATE EMPTY LIST OF ENEMIES
        this.enemies = new ArrayList<>();
        this.bulletsShot = new ArrayList<>();
        this.interactables = new ArrayList<>();
        // CREATE BACKGROUND
        this.background = new Background(enemies, interactables, this);
        this.inputTest = new InputTest(background);
        this.inputTest.init();
        this.currentRoom = 0;
        this.spawnTimer = System.currentTimeMillis() - this.spawnDelay;
    }

    public void play() {

        this.player = new Player(bulletsShot);
        this.background.setPlayer(this.player);
        this.inputTest.setPlayer(this.player);
        enemies.add(new Enemy(10, 10, this.player, 10));
        this.hud = new HUD();
        this.hud.setPlayer(this.player);
        this.collisionManager = new CollisionManager(background, player, enemies, bulletsShot, interactables, this);

        try {
            this.gameLoop();
        } catch (InterruptedException e) {
            System.out.println("Waiting...");
        }
    }

    public void gameLoop() throws InterruptedException {
        while (player.getHealth() > 0) {

            if(System.currentTimeMillis() - spawnTimer >= spawnDelay) {
                for (int i = 0; i < 5; i++) {
                    int x = (int) (Math.random() * 500) + player.getPosition()[0];
                    int y = (int) (Math.random() * 500) + player.getPosition()[1];


                    enemies.add(new Enemy(x, y, player, 7));
                }
            }


            // MOVE THE BACKGROUND TO SIMULATE PLAYER MOVEMENT WITH CAMERA FOLLOW
            this.background.moveTo();
            // PLAYER SHOOTS
            this.player.shoot(enemies);
            // CHECK FOR ALL COLLISION AND MOVE ALL OBJECTS EXCEPT THE PLAYER
            collisionManager.checkCollisions();
            // DEBUG
            // System.out.println("Enemy is " + enemies.get(0).getHealth() + " HP");
            // System.out.println("BULLETS LIST HAS " + bulletsShot.size() + " BULLETS");
            // SHOW HUD
            this.hud.show();
            // WAITS
            Thread.sleep(10);

        }

        GameOver gameOver = new GameOver();
    }

    public void incRoom() {
        this.currentRoom++;
    }

    public int getRoom() {
        return this.currentRoom;
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

        KeyboardEvent eKeyPress = new KeyboardEvent();
        eKeyPress.setKey(KEY_E);
        eKeyPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent eKeyRelease = new KeyboardEvent();
        eKeyRelease.setKey(KEY_E);
        eKeyRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(eKeyPress);
        keyboard.addEventListener(eKeyRelease);



    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_W) {this.player.setWDir(1);}
        if(keyboardEvent.getKey() == KEY_S) {this.player.setSDir(1);}
        if(keyboardEvent.getKey() == KEY_A) {this.player.setADir(1);}
        if(keyboardEvent.getKey() == KEY_D) {this.player.setDDir(1);}
        if(keyboardEvent.getKey() == KEY_E) {this.player.PressEKey();}
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KEY_W) {this.player.setWDir(0);}
        if(keyboardEvent.getKey() == KEY_S) {this.player.setSDir(0);}
        if(keyboardEvent.getKey() == KEY_A) {this.player.setADir(0);}
        if(keyboardEvent.getKey() == KEY_D) {this.player.setDDir(0);}
        if(keyboardEvent.getKey() == KEY_E) {this.player.ReleaseEKey();}
    }
}
