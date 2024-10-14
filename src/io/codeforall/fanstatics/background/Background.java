package src.io.codeforall.fanstatics.background;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Background {

    private Rectangle sprite;
    private ArrayList<Enemy> enemies;

    private int[] initialPosition;
    private int[] offset;

    public Background(int screenWidth, int screenHeight, ArrayList<Enemy> enemies) {
        this.sprite = new Rectangle(0, 0, screenWidth, screenHeight);
        this.sprite.setColor(Color.DARK_GRAY);
        this.sprite.fill();

        this.enemies = enemies;

        this.offset = new int[] {0, 0};
    }

    public int[] getOffset() {
        return this.offset;
    }

    public void setOffset(int[] newOffset) {
        this.offset = newOffset;
    }

    public void move(int[] translate) {
        System.out.println("MOVED TRANSLATE: " + translate[0] + " " + translate[1]);
        this.sprite.translate(translate[0], translate[1]);
        this.sprite.fill();

        for(Enemy enemy : enemies) {
            enemy.move(translate);
        }

    }

}
