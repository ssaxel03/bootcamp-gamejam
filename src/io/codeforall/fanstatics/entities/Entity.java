package src.io.codeforall.fanstatics.entities;

import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;

public abstract class Entity extends BoxCollider implements Collideable {

    public static final int SPRITE_SIZE = 100;

    BoxCollider boxCollider;
    int health;
    int speed;
    String entityName;


    public Entity(int x, int y, int speed, String entityName) {
        super(x, y, SPRITE_SIZE, SPRITE_SIZE);
        this.boxCollider = new BoxCollider(x, y, SPRITE_SIZE, SPRITE_SIZE);
        this.health = 100;
        this.speed = speed;
        this.entityName = entityName;
    }

    public void hit(int damage) {

        health = damage > health ? 0 : health - damage;

    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public String getName() {
        return this.entityName;
    }
}
