package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;

public class Bullet {

    public final int SPEED = 10;
    public final int BULLET_SIZE = 5;
    private int damage;
    private Rectangle sprite;
    private BoxCollider boxCollider;
    private float[] normalizedDirection;

    // Constructor
    public Bullet(int damage, int x, int y, float[] normalizedDirection) {
        this.damage = damage;
        this.sprite = new Rectangle(x, y, BULLET_SIZE, BULLET_SIZE);
        this.boxCollider = new BoxCollider(x, y, BULLET_SIZE, BULLET_SIZE);
        this.normalizedDirection = normalizedDirection;
    }

    // Method to move the bullet
    public void move() {
        int translateX = (int) (normalizedDirection[0] * this.SPEED);
        int translateY = (int) (normalizedDirection[1] * this.SPEED);

        this.sprite.translate(translateX, translateY);
        this.boxCollider.move(new int[] {translateX, translateY});
    }

    public void translate(int translateX, int translateY) {

        this.sprite.translate(translateX, translateY);
        this.boxCollider.move(new int[] {translateX, translateY});

    }

    // Getters
    public int getDamage() {
        return damage;
    }
}
