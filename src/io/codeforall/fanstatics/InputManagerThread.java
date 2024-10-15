package src.io.codeforall.fanstatics;

public class InputManagerThread extends Thread {

    GameManager game;

    public InputManagerThread(GameManager game) {
        super();
        this.game = game;
    }

    public void run() {
        InputManager inputManager = new InputManager(game);
    }
}
