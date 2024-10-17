package src.io.codeforall.fanstatics.background;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;
import src.io.codeforall.fanstatics.Vectors;
import src.io.codeforall.fanstatics.entities.Enemy;
import src.io.codeforall.fanstatics.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Background implements Collideable {

    private BoxCollider boxCollider;

    private Rectangle sprite;
    private ArrayList<Enemy> enemies;
    private Player player;

    public Background(int screenWidth, int screenHeight, ArrayList<Enemy> enemies) {
        this.boxCollider = new BoxCollider(10, 10, screenWidth - 20, screenHeight - 20);

        this.sprite = new Rectangle(0, 0, screenWidth, screenHeight);
        this.sprite.setColor(Color.DARK_GRAY);
        this.sprite.fill();

        this.enemies = enemies;

    }

    public void moveTo(int[] goalPosition) {

        // System.out.println("GOAL BG POSITION: [" + goalPosition[0] + ", " + goalPosition[1] + "]");
        // System.out.println("CURRENT BG POSITION POSITION: [" + this.sprite.getX() + ", " + this.sprite.getY() + "]");

        int directionX = this.player.getADir() - this.player.getDDir();
        int directionY = this.player.getWDir() - this.player.getSDir();
        System.out.println(directionX);
        System.out.println(directionY);

        float vectorLength = (float) Math.sqrt( (directionX * directionX) +
                (directionY * directionY) );

        if(vectorLength == 0) {
            return;
        }
        try {
            directionX = (int) ((directionX * this.player.getSpeed()) / vectorLength);
            directionY = (int) ((directionY * this.player.getSpeed()) / vectorLength);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.move(new int[] {directionX, directionY});
    }

    public void move(int[] translate) {
        // System.out.println("MOVED TRANSLATE: " + translate[0] + " " + translate[1]);

        this.sprite.translate(translate[0], translate[1]);
        this.sprite.fill();

        this.boxCollider.move(translate);

        for (Enemy enemy : enemies) {
            enemy.move(translate);
        }

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Rectangle getSprite() {
        return this.sprite;
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
