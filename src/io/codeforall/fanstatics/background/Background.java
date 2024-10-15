package src.io.codeforall.fanstatics.background;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;
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

        int[] direction = new int[] { (goalPosition[0] - this.sprite.getX()), (goalPosition[1] - this.sprite.getY())};

        int[] spriteGoalPosition = new int[] {this.sprite.getX() + direction[0], this.sprite.getY() + direction[1]};

        if(spriteGoalPosition[0] == this.sprite.getX() && spriteGoalPosition[1] == this.sprite.getY()) {
            return;
        }

        // CALCULATE DISTANCE OF DIRECTION TO PLAYER
        double arrayLength = Math.sqrt( (direction[0] * direction[0]) + (direction[1] * direction[1]) );

        // CREATE NEW DIRECTION VECTOR ALWAYS WITH THE SAME SIZE OR SMALLER IF VERY CLOSE
        // MOVE BACKGROUND AND ALL OF ITS COMPONENTS
        if(arrayLength < this.player.getSpeed()) {
            this.move(direction);
        } else {
            direction[0] = (int) (this.player.getSpeed() * direction[0] / arrayLength);
            direction[1] = (int) (this.player.getSpeed() * direction[1] / arrayLength);
            this.move(direction);
        }
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
