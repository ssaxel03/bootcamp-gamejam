package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class GameOver {
    private Picture gameOver;
    private final String gameOverFilePath = "assets/gameOver.png";

    public GameOver() {
        this.gameOver = new Picture(0, 0, gameOverFilePath);
        this.gameOver.draw();
    }

}
