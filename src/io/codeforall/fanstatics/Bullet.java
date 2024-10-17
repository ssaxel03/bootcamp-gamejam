package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;

import java.util.ArrayList;

public class Bullet implements Collideable{

    public final int SPEED = 30;
    public final int BULLET_WIDTH = 50;
    public final int BULLET_HEIGHT = 5;
    private int damage;
    private Rectangle sprite;
    private BoxCollider boxCollider;
    private float[] normalizedDirection;

    private final long despawnTimeMs = 500;
    private final long spawnTimeMs;

    // Constructor
    public Bullet(int damage, int x, int y, float[] normalizedDirection) {
        this.damage = damage;
        this.sprite = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.sprite.setColor(Color.GREEN);
        this.sprite.fill();
        this.boxCollider = new BoxCollider(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.boxCollider.getBounds().grow(100, 100);
        this.normalizedDirection = normalizedDirection;
        this.spawnTimeMs = System.currentTimeMillis();
        System.out.println(this.spawnTimeMs);
        System.out.println("BULLET CREATED");
    }

    // Method to move the bullet
    public void move() {
        int translateX = (int) (normalizedDirection[0] * this.SPEED);
        int translateY = (int) (normalizedDirection[1] * this.SPEED);

        this.translate(translateX, translateY);
    }

    public void translate(int translateX, int translateY) {

        this.sprite.translate(translateX, translateY);
        this.boxCollider.move(new int[] {translateX, translateY});

    }

    // Getters
    public int getDamage() {
        return damage;
    }

    public boolean checkDespawn() {
        if(System.currentTimeMillis() - this.spawnTimeMs >= this.despawnTimeMs) {
            this.sprite.delete();
            return true;
        }
        return false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void onCollision(Collideable col) {
        switch (col.getName()) {
            case "Enemy", "Wall", "Door", "Obstacle" -> this.sprite.delete();
        }
    }

    @Override
    public String getName() {
        return "Bullet";
    }
}
