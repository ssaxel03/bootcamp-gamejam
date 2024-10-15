package src.io.codeforall.fanstatics;

public class GameManagerThread extends Thread {

    GameManager game;
    public GameManagerThread(GameManager game) {
        this.game = game;
    }

    public void run() {
        game.play();
    }

}
