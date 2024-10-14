package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;
import src.io.codeforall.fanstatics.GameManager;

public class Player extends Entity implements Collideable {

    private BoxCollider boxCollider;

    private Rectangle sprite;
    private PlayerController playerController;

    private int[] position;

    public Player() {
        super(GameManager.SCREEN_WIDTH / 2 - Entity.SPRITE_SIZE / 2,
                GameManager.SCREEN_HEIGHT / 2 - Entity.SPRITE_SIZE / 2,
                2,
                "Player");

        this.playerController = new PlayerController(
                this.sprite = new Rectangle(GameManager.SCREEN_WIDTH / 2 - super.SPRITE_SIZE / 2,
                        GameManager.SCREEN_HEIGHT / 2 - super.SPRITE_SIZE / 2,
                        super.SPRITE_SIZE,
                        super.SPRITE_SIZE));

        this.position = new int[]{this.sprite.getX(), this.sprite.getY()};


    }

    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void onCollision(Collideable col) {
        // PLAYER COLLISION METHOD

        System.out.println("Player collided with " + col.getName());

        switch(col.getName()){
            case "Enemy":
                this.hit(2);
                System.out.println("PLAYER GOT HIT (" + super.health + ")");

        }

        return;
    }

    @Override
    public String getName() {
        return super.entityName;
    }
}
