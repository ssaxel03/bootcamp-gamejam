package src.io.codeforall.fanstatics.entities;

import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;

import java.awt.*;

public abstract class Entity implements Collideable {

    public static final int SPRITE_SIZE = 100;

    BoxCollider boxCollider;
    int health;
    boolean isDead;
    int speed;
    String entityName;


    public Entity(int x, int y, int speed, String entityName) {
        this.boxCollider = new BoxCollider(x, y, SPRITE_SIZE, SPRITE_SIZE);
        this.health = 100;
        this.speed = speed;
        this.entityName = entityName;
        this.isDead = false;
    }

    public void hit(int damage) {

        health = damage > health ? 0 : health - damage;

    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public Rectangle getBounds() {
        return this.boxCollider.getBounds();
    }

    public String getName() {
        return this.entityName;
    }
    public boolean isDead() {
        return this.isDead;
    }
}
