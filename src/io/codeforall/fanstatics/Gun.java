package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.Bullet;

import javax.swing.*;
import java.util.ArrayList;

public class Gun {
    public final int GUN_WIDTH = 10;
    public final int GUN_HEIGHT = 5;
    private int ammo;
    private Rectangle sprite;
    private GunType gunType;
    private ArrayList<Bullet> bulletsShot;
    private long reloadTimerMs;
    private long lastShotTimerMs;
    private boolean reloading;

    // Constructor
    public Gun(int x, int y, GunType gunType, ArrayList<Bullet> bulletsShot) {
        this.ammo = 100;
        this.sprite = new Rectangle(x, y, GUN_WIDTH, GUN_HEIGHT);
        this.gunType = gunType;
        this.bulletsShot = bulletsShot;
        this.lastShotTimerMs = System.currentTimeMillis() - gunType.getFireRateDelayMs();
        this.reloading = false;
    }

    // Method to shoot
    public void shoot(int x, int y, float[] normalizedDirection) {
        System.out.println("WEAPON IS SHOOTING");

        System.out.println("Time since last shot " + (System.currentTimeMillis() - lastShotTimerMs));

        // IF THE WEAPONS STILL HAS AMMO LEFT AND THERE HAS PASSED ENOUGH TIME SINCE THE LAST SHOT THE WEAPON SHOOTS
        if(ammo > 0 && System.currentTimeMillis() - lastShotTimerMs >= gunType.getFireRateDelayMs()) {
            bulletsShot.add(new Bullet(gunType.getDamage(), x, y, normalizedDirection));
            this.lastShotTimerMs = System.currentTimeMillis();
        }
    }

    // Method to reload
    public void reload(int ammo) {
        // IF THE PLAYER IS NOT RELOADING IT STARTS RELOADING AND WITH IT A START TIME FOR THE RELOAD IS SET
        if(!reloading) {
            this.reloading = true;
            this.reloadTimerMs = (int) System.currentTimeMillis();
            return;
        }
        // IF THE PLAYER IS ALREADY RELOADING AND HAS BEEN DOING IT FOR THE TIME REQUIRED BY THE WEAPON FINISHES RELOADING PROCESS
        if(System.currentTimeMillis() - this.reloadTimerMs >= 500 /*GUN TYPE*/) {
            this.reloading = false;
            this.ammo = ammo;
        }
    }

    // Getters
    public int getAmmo() {
        return this.ammo;
    }

}

