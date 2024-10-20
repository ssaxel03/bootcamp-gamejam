package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import src.io.codeforall.fanstatics.*;

import java.util.ArrayList;

public class Player extends Entity implements Collideable {

    private Rectangle sprite;
    private Gun gun;
    private int money;
    private int bullets;
    private Enemy targetEnemy;
    private Picture reloadingPopUp;
    private final String reloadingFilePath = "assets/reloading.png";

    int wDir;
    int sDir;
    int aDir;
    int dDir;
    boolean eKey;
    boolean rKey;
    boolean reloading;

    private int[] position;

    public Player(ArrayList<Bullet> bulletsShot) {
        // ENTITY CONSTRUCTOR
        super(1100 - Entity.SPRITE_SIZE / 2,
                600 - Entity.SPRITE_SIZE / 2,
                10,
                "Player");
        // CREATE SPRITE
        this.sprite = new Rectangle(1100 - super.SPRITE_SIZE / 2,
                600 - super.SPRITE_SIZE / 2,
                super.SPRITE_SIZE,
                super.SPRITE_SIZE);
        this.sprite.setColor(Color.BLUE);
        this.sprite.fill();
        // CREATE RELOADING POP UP
        this.reloadingPopUp = new Picture(this.sprite.getX() - SPRITE_SIZE / 2, this.sprite.getY() - 200, reloadingFilePath);
        this.reloadingPopUp.draw();
        this.reloadingPopUp.delete();
        // CREATE THE DEFAULT GUN
        this.gun = new Gun(0, 0, GunType.PISTOL, bulletsShot);
        this.gun.setAmmo(12);
        // GIVE THE PLAYER NO MONEY AND NO BULLETS
        this.money = 50;
        this.bullets = 10;
        // CREATE THE TARGET ENEMY AS NULL
        this.targetEnemy = null;
        // INITIAL VECTOR MOVEMENT
        wDir = 0;
        sDir = 0;
        aDir = 0;
        dDir = 0;
        // INITIAL KEYS VALUE
        eKey = false;
        rKey = false;
        // SAVE INITIAL POSITION
        this.position = new int[]{this.sprite.getX(), this.sprite.getY()};
        this.reloading = false;
    }

    public void shoot(ArrayList<Enemy> enemies) {

        drawReload();

        System.out.println("GUN HAS " + this.getGunBullets() + " BULLETS");

        if(rKey && this.bullets > 0 && this.gun.getAmmo() <= this.gun.getGunType().getBullets()) {
            System.out.println("START OF RELOADING");
            this.reloading = true;
            int maxReload = this.gun.getGunType().getBullets() - this.gun.getAmmo();
            int reloadAmount = Math.min(Math.min(bullets, gun.getGunType().getBullets()), maxReload);
            this.bullets -= reloadAmount;
            this.gun.reload(reloadAmount);
        }

        if(!this.gun.isReloaded()) {
            System.out.println("RELOADING");
            return;
        }

        this.reloading = false;

        System.out.println("GUN HAS " + this.getGunBullets() + " BULLETS 2");

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
        this.gun.shoot(this.position[0] + SPRITE_SIZE / 2,
                this.position[1] + SPRITE_SIZE / 2,
                Vectors.getNormalizedDirection(targetEnemy.getPositionX() - (this.position[0] + SPRITE_SIZE / 2),
                        targetEnemy.getPositionY() - (this.position[1] + SPRITE_SIZE / 2 )));
        // DEBUG
        // System.out.println("SHOTS FIRED");
        // IF THE ENEMY DIES, RESET THE TARGET ENEMY
        if(this.targetEnemy.getHealth() <= 0) {
            this.targetEnemy = null;
        }
    }

    public void drawReload() {
        if(reloading) {
            this.reloadingPopUp.delete();
            this.reloadingPopUp.draw();
            return;
        }
        this.reloadingPopUp.delete();
    }
    public void draw() {
        this.sprite.delete();
        this.sprite.fill();
    }

    @Override
    public void onCollision(Collideable col) {
        // PLAYER COLLISION METHOD
        // DEBUG
        // System.out.println("Player collided with " + col.getName());
        System.out.println("COLISAO");
        if (col.getName().equals("Enemy")) {
            Enemy attacker = (Enemy) col;
            System.out.println("TOU AQUI");
            if(attacker.canAttack()) {
                this.hit(attacker.getDamage());
                System.out.println("PLAYER GOT HIT");
                return;
            }
            System.out.println("Enemy waiting");
        }
    }

    public void pay(int amount) {
        this.money -= amount;
        System.out.println("PAID " + amount);
    }
    public int getMoney() {
        return this.money;
    }
    public void incMoney(int amount) {
        this.money = Math.min(money + amount, 1000);
        System.out.println("INCREASED " + this.money);
    }
    public int getHealth() {
        return this.health;
    }
    public int getBullets() {
        return this.bullets;
    }
    public int getGunBullets() {
        return this.gun.getAmmo();
    }

    public GunType getGunType() {
        return this.gun.getGunType();
    }
    public void giveGun(GunType gunType) {
        this.gun.setGunType(gunType);
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

    public void PressEKey() {
        this.eKey = true;
        System.out.println("E KEY");
    }

    public void ReleaseEKey() {
        this.eKey = false;
    }

    public boolean getEKey() {
        return this.eKey;
    }

    public void pressRKey() {
        this.rKey = true;
        System.out.println("PRESSED RELOAD");
    }

    public void releaseRKey() {
        this.rKey = false;
    }

    public void incBullets(int amount) {
        this.bullets += amount;
    }
}
