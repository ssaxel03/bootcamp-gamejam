package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {

    private Picture menuPlay;
    private final String menuPlayFilePath = "assets/menuPlay.png";
    private Picture menuQuit;
    private final String menuQuitFilePath = "assets/menuQuit.png";
    private boolean play;
    private boolean start;
    private Keyboard keyboard;

    public Menu() {
        this.play = true;
        this.menuQuit = new Picture(0, 0, menuQuitFilePath);
        this.menuQuit.draw();
        this.menuPlay = new Picture(0, 0, menuPlayFilePath);
        this.menuPlay.draw();
        this.start = false;

        this.keyboardInit();
    }

    public void run() {
        try {
            this.menuLoop();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        if(start) {
            GameManager gameManager = new GameManager();
            gameManager.play();
        } else {
            System.exit(0);
        }
    }

    public void menuLoop() throws InterruptedException {
        while(!start) {
            if (play) {
                this.menuQuit.delete();
                this.menuPlay.draw();
            } else {
                this.menuPlay.delete();
                this.menuQuit.draw();
            }
            Thread.sleep(10);
        }
    }

    private void keyboardInit() {
        this.keyboard = new Keyboard(this);

        KeyboardEvent upArrow = new KeyboardEvent();
        upArrow.setKey(KeyboardEvent.KEY_RIGHT);
        upArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent downArrow = new KeyboardEvent();
        downArrow.setKey(KeyboardEvent.KEY_DOWN);
        downArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_SPACE);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        this.keyboard.addEventListener(upArrow);
        this.keyboard.addEventListener(downArrow);
        this.keyboard.addEventListener(enter);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN ||
        keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            this.play = !play;
        } else {
            this.start = true;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
