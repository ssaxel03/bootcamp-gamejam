package src.io.codeforall.fanstatics;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        GameManager game = new GameManager();

        InputManagerThread inputManagerThread = new InputManagerThread(game);
        inputManagerThread.start();

        GameManagerThread gameManagerThread = new GameManagerThread(game);
        gameManagerThread.start();
    }
}
