package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.BoxCollider;
import src.io.codeforall.fanstatics.Collideable;
import src.io.codeforall.fanstatics.GameManager;

public class Player extends Entity implements Collideable {

    private BoxCollider boxCollider;

    private Rectangle sprite;
    private PlayerController playerController;

    boolean wDir;
    boolean sDir;
    boolean aDir;
    boolean dDir;

    private int[] position;

    public Player() {
        super(GameManager.SCREEN_WIDTH / 2 - Entity.SPRITE_SIZE / 2,
                GameManager.SCREEN_HEIGHT / 2 - Entity.SPRITE_SIZE / 2,
                15,
                "Player");

        wDir = false;
        sDir = false;
        aDir = false;
        dDir = false;

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

    public int getSpeed() {
        return this.speed;
    }

    public boolean getWDir() {
        return this.wDir;
    }
    public boolean getSDir() {
        return this.sDir;
    }
    public boolean getADir() {
        return this.aDir;
    }
    public boolean getDDir() {
        return this.dDir;
    }
    public void setDir(
            boolean wDir, boolean sDir,
            boolean aDir, boolean dDir) {

        this.wDir = wDir;
        this.sDir = sDir;
        this.aDir = aDir;
        this.dDir = dDir;
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
}
