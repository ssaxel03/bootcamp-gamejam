package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.*;

import java.util.ArrayList;

public class Player extends Entity implements Collideable {

    private Rectangle sprite;
    private Gun gun;
    private Enemy targetEnemy;

    int wDir;
    int sDir;
    int aDir;
    int dDir;

    private int[] position;

    public Player(ArrayList<Bullet> bulletsShot) {
        // ENTITY CONSTRUCTOR
        super(GameManager.SCREEN_WIDTH / 2 - Entity.SPRITE_SIZE / 2,
                GameManager.SCREEN_HEIGHT / 2 - Entity.SPRITE_SIZE / 2,
                10,
                "Player");
        // CREATE SPRITE
        this.sprite = new Rectangle(GameManager.SCREEN_WIDTH / 2 - super.SPRITE_SIZE / 2,
                GameManager.SCREEN_HEIGHT / 2 - super.SPRITE_SIZE / 2,
                super.SPRITE_SIZE,
                super.SPRITE_SIZE);
        this.sprite.setColor(Color.BLUE);
        this.sprite.fill();
        // CREATE THE DEFAULT GUN
        this.gun = new Gun(0, 0, GunType.RIFLE, bulletsShot);
        // CREATE THE TARGET ENEMY AS NULL
        this.targetEnemy = null;
        // INITIAL VECTOR MOVEMENT
        wDir = 0;
        sDir = 0;
        aDir = 0;
        dDir = 0;
        // SAVE INITIAL POSITION
        this.position = new int[]{this.sprite.getX(), this.sprite.getY()};
    }

    public void shoot(ArrayList<Enemy> enemies) {
        // IF THERE IS NO TARGET THE PLAYER FOCUSES ON THE CLOSEST ONE
        if(this.targetEnemy == null) {
            int shortestDistance = Integer.MAX_VALUE;
            for(Enemy enemy : enemies) {
                if(Vectors.getVectorLength(
                        enemy.getPositionX() - this.position[0],
                        enemy.getPositionY() - this.position[1]) < shortestDistance && enemy.getHealth() > 0) {
                    this.targetEnemy = enemy;
                }
            }
        }
        // IF THERE ARE NO TARGETS LEFT THE PLAYER DOESN'T SHOOT
        if(this.targetEnemy == null) {
            return;
        }
        // THE PLAYER SHOOTS THE ENEMY
        this.gun.shoot(this.position[0],
                this.position[1],
                Vectors.getNormalizedDirection(targetEnemy.getPositionX() - this.position[0],
                        targetEnemy.getPositionY() - this.position[1]));
        System.out.println("SHOTS FIRED");
        // IF THE ENEMY DIES, RESET THE TARGET ENEMY
        if(this.targetEnemy.getHealth() <= 0) {
            this.targetEnemy = null;
        }
    }

    @Override
    public void onCollision(Collideable col) {
        // PLAYER COLLISION METHOD

        System.out.println("Player collided with " + col.getName());

        switch (col.getName()) {
            case "Enemy":
                this.hit(2);
                System.out.println("PLAYER GOT HIT (" + super.health + ")");

        }

        return;
    }

    public int[] getPosition() {
        return this.position;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getWDir() {
        return this.wDir;
    }

    public int getSDir() {
        return this.sDir;
    }

    public int getADir() {
        return this.aDir;
    }

    public int getDDir() {
        return this.dDir;
    }

    public void setWDir(int wDir) {
        this.wDir = wDir;
    }

    public void setSDir(int sDir) {
        this.sDir = sDir;
    }

    public void setADir(int aDir) {
        this.aDir = aDir;
    }

    public void setDDir(int dDir) {
        this.dDir = dDir;
    }
}
