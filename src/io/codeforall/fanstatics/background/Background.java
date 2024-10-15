package src.io.codeforall.fanstatics.background;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;
import src.io.codeforall.fanstatics.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Background implements Collideable {

    private BoxCollider boxCollider;

    private Rectangle sprite;
    private ArrayList<Enemy> enemies;

    private int[] initialPosition;
    private int[] offset;

    public Background(int screenWidth, int screenHeight, ArrayList<Enemy> enemies) {
        this.boxCollider = new BoxCollider(10, 10, screenWidth - 10, screenHeight - 10);

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

    public void incOfsset(int[] translate) {

    }

    public void move(int[] translate) {
        System.out.println("MOVED TRANSLATE: " + translate[0] + " " + translate[1]);
        this.sprite.translate(translate[0], translate[1]);
        this.sprite.fill();
        this.boxCollider.move(translate);

        for(Enemy enemy : enemies) {
            enemy.move(translate);
            enemy.getBoxCollider().move(translate);
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void onCollision(Collideable col) {

    }

    @Override
    public String getName() {
        return "Background";
    }
}
