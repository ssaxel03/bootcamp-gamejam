package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.Bullet;

import javax.swing.*;
import java.util.ArrayList;

public class Gun {
    public final int GUN_WIDTH = 10;
    public final int GUN_HEIGHT = 5;
    private int ammo;
    private Rectangle sprite;
    // private GunType gunType;
    private ArrayList<Bullet> bulletsShot;
    private int reloadTimerMs;
    private int fireRateTimerMs;
    private boolean reloading;

    // Constructor
    public Gun(int x, int y, ArrayList<Bullet> bulletsShot) {
        this.ammo = 0;
        this.sprite = new Rectangle(x, y, GUN_WIDTH, GUN_HEIGHT);
        this.bulletsShot = bulletsShot;
        this.reloadTimerMs = 0;
        this.fireRateTimerMs = (int) System.currentTimeMillis();
        this.reloading = false;
    }

    // Method to shoot
    public void shoot(int x, int y, float[] normalizedDirection) {

        if(ammo > 0) {
            bulletsShot.add(new Bullet(10/* GUNTYPE*/, x, y, normalizedDirection));
            return;
        }

        System.out.println("NO AMMO");

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

