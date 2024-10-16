package src.io.codeforall.fanstatics.entities;

import javax.swing.*;

public class Gun {
    private int ammunition;       // Current ammunition
    private int maxAmmunition;    // Maximum ammunition capacity
    private ImageIcon sprite;      // Image of the gun

    // Constructor
    public Gun(int maxAmmunition, String spritePath) {
        this.maxAmmunition = maxAmmunition;
        this.ammunition = maxAmmunition; // Start with full ammo
        this.sprite = new ImageIcon(spritePath); // Load gun sprite
    }

    // Method to shoot
    public void shoot() {
        if (ammunition > 0) {
            ammunition--;
            System.out.println("Fire! Ammunition left: " + ammunition);
        } else {
            System.out.println("Out of ammunition!");
        }
    }

    // Method to reload
    public void reload() {
        ammunition = maxAmmunition;
        System.out.println("Reloaded! Ammunition is now: " + ammunition);
    }

    // Getters
    public int getAmmunition() {
        return ammunition;
    }

    public int getMaxAmmunition() {
        return maxAmmunition;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

}

