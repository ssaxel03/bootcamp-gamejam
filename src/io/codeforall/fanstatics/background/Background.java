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

        // ATTRIBUTE DIRECTION OF BACKGROUND MOVEMENT
        int directionX = (goalPosition[0] - this.sprite.getX());
        int directionY = (goalPosition[1] - this.sprite.getY());

        // CHECKS IF WE ARE CLOSE ENOUGH TO THE GOAL POSITION SO THAT OUR NORMALIZED VECTOR TIMES SPEED ARE GOING TO SURPASS THE GOAL POSITION
        if(Vectors.getVectorLength(directionX, directionY) < this.player.getSpeed()) {
            // MOVES JUST THE AMOUNT NEEDED TO REACH THE GOAL POSITION
            this.move(new int[] {directionX, directionY});
        } else {
            // MOVES THE MAXIMUM AMOUNT ALLOWED BY THE SPEED ATTRIBUTE OF THE PLAYER
            float[] normalizedArray = Vectors.getNormalizedDirection(directionX, directionY);

            // CREATES THE MOVE ARRAY ACCORDING TO THE PLAYER SPEED
            int[] moveArray = new int[] {(int) (normalizedArray[0] * this.player.getSpeed()), (int) (normalizedArray[1] * this.player.getSpeed())};

            // MOVES THE BACKGROUND
            this.move(moveArray);
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
