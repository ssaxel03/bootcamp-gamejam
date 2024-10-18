package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import src.io.codeforall.fanstatics.entities.Player;

import java.lang.Math;

public class HUD {
    // PLAYER
    private Player player;
    // HEALTH
    private Picture playerHealthFrame;
    private final String playerHealthFrameFilePath = "assets/playerHealthBg.png";
    private Picture playerHealthOverlay;
    private final String playerHealthOverlayFilePath = "assets/playerHealthOverlay.png";
    // MONEY
    private Picture playerMoneyFrame;
    private final String playerMoneyFrameFilePath = "assets/playerMoneyFrame.png";
    private Picture playerMoneyOverlay;
    private final String playerMoneyOverlayFilePath = "assets/playerMoneyOverlay.png";
    private Picture playerMoneyBuyADoorAlert;
    private final String playerMoneyBuyADoorAlertFilePath = "assets/buyADoorAlert.png";
    // PLAYER BULLETS
    private Picture playerFace;
    private final String playerFaceFilePath = "assets/playerFace.png";
    private Picture playerBulletsFrame;
    private final String playerBulletsFrameFilePath = "assets/playerBulletsFrame.png";
    private Picture playerBulletsOverlay;
    private final String playerBulletsOverlayFilePath = "assets/playerBulletsOverlay.png";
    // GUN BULLETS
    private Picture pistolIcon;
    private final String pistolIconFilePath = "assets/pistolIcon.png";
    private Picture rifleIcon;
    private final String rifleIconFilePath = "assets/rifleIcon.png";
    private Picture shotgunIcon;
    private final String shotgunIconFilePath = "assets/shotgunIcon.png";
    private Picture gunBulletsFrame;
    private final String gunBulletsFrameFilePath = "assets/gunBulletsFrame.png";
    private Picture gunBulletsOverlay;
    private final String gunBulletsOverlayFilePath = "assets/gunBulletsOverlay.png";


    private int deltaHealth;
    private int lastHealth;
    private int deltaMoney;
    private int lastMoney;
    private int deltaPlayerBullets;
    private int lastPlayerBullets;
    private int deltaGunBullets;
    private int lastGunBullets;

    public HUD() {
        // HEALTH
        playerHealthOverlay = new Picture(100, 100, playerHealthOverlayFilePath);
        playerHealthOverlay.draw();
        playerHealthFrame = new Picture(100, 100, playerHealthFrameFilePath);
        playerHealthFrame.draw();
        // MONEY
        playerMoneyBuyADoorAlert = new Picture(103, 140, playerMoneyBuyADoorAlertFilePath);
        playerMoneyBuyADoorAlert.draw();
        playerMoneyOverlay = new Picture(100, 150, playerMoneyOverlayFilePath);
        playerMoneyOverlay.grow(-142, 0);
        playerMoneyOverlay.translate(-142, 0);
        playerMoneyOverlay.draw();
        playerMoneyFrame = new Picture(100, 150, playerMoneyFrameFilePath);
        playerMoneyFrame.draw();
        // PLAYER BULLETS
        playerFace = new Picture(100, 185, playerFaceFilePath);
        playerFace.draw();
        playerBulletsOverlay = new Picture(135, 185, playerBulletsOverlayFilePath);
        playerBulletsOverlay.draw();
        playerBulletsOverlay.grow(-42, 0);
        playerBulletsOverlay.translate(-42, 0);
        playerBulletsFrame = new Picture(135, 185, playerBulletsFrameFilePath);
        playerBulletsFrame.draw();
        // GUN BULLETS
        pistolIcon = new Picture(265, 185, pistolIconFilePath);
        rifleIcon = new Picture(265, 185, rifleIconFilePath);
        shotgunIcon = new Picture(265, 185, shotgunIconFilePath);
        gunBulletsOverlay = new Picture(300, 185, gunBulletsOverlayFilePath);
        gunBulletsOverlay.draw();
        gunBulletsOverlay.grow(-33, 0);
        gunBulletsOverlay.translate(-33, 0);
        gunBulletsFrame = new Picture(300, 185, gunBulletsFrameFilePath);
        gunBulletsFrame.draw();
    }

    public void setPlayer(Player player) {
        this.player = player;
        // HEALTH
        this.deltaHealth = player.getHealth();
        this.lastHealth = player.getHealth();
        // MONEY
        this.deltaMoney = player.getMoney();
        this.lastMoney = player.getMoney();
        // PLAYER BULLETS
        this.deltaPlayerBullets = player.getBullets();
        this.lastPlayerBullets = player.getBullets();
        // GUN BULLETS
        this.deltaGunBullets = player.getGunBullets();
        this.lastGunBullets = player.getGunBullets();
    }

    public void show() {

        switch(player.getGunType()) {
            case PISTOL:
                this.pistolIcon.draw();
                this.rifleIcon.delete();
                this.shotgunIcon.delete();
                break;
            case RIFLE:
                this.pistolIcon.delete();
                this.rifleIcon.draw();
                this.shotgunIcon.draw();
                break;
            case SHOTGUN:
                this.pistolIcon.delete();
                this.rifleIcon.delete();
                this.pistolIcon.draw();
                break;
        }

        this.deltaHealth = player.getHealth() - this.lastHealth;
        this.lastHealth = player.getHealth();
        this.deltaMoney = player.getMoney() - this.lastMoney;
        this.lastMoney = player.getMoney();
        this.deltaPlayerBullets = player.getBullets() - this.lastPlayerBullets;
        this.lastPlayerBullets = player.getBullets();
        this.deltaGunBullets = player.getGunBullets() - this.lastGunBullets;
        this.lastGunBullets = player.getGunBullets();

        playerHealthOverlay.grow(this.deltaHealth * 3 == -playerHealthOverlay.getWidth() ? this.deltaHealth * 3 / 2 + 1 : this.deltaHealth * 3 / 2, 0);
        playerHealthOverlay.translate(this.deltaHealth * 3 == -playerHealthOverlay.getWidth() ? this.deltaHealth * 3 / 2 + 1 : this.deltaHealth * 3 / 2, 0);

        playerMoneyOverlay.grow(this.deltaMoney / 3 == -playerMoneyOverlay.getWidth() ? this.deltaMoney / 3 / 2 + 1 : this.deltaMoney / 3 / 2, 0);
        playerMoneyOverlay.translate(this.deltaMoney / 3 == -playerMoneyOverlay.getWidth() ? this.deltaMoney / 3 / 2 + 1 : this.deltaMoney / 3 / 2, 0);

        playerBulletsOverlay.grow(this.deltaPlayerBullets * 5 == -playerBulletsOverlay.getWidth() ? this.deltaPlayerBullets * 5 / 2 + 1 : this.deltaPlayerBullets * 5 / 2, 0);
        playerMoneyOverlay.translate(this.deltaPlayerBullets * 5 == -playerBulletsOverlay.getWidth() ? this.deltaPlayerBullets * 5 / 2 + 1 : this.deltaPlayerBullets * 5 / 2, 0);

        gunBulletsOverlay.grow(this.deltaGunBullets * 10 == -gunBulletsOverlay.getWidth() ? this.deltaGunBullets * 10 + 1 : this.deltaGunBullets * 10 / 2, 0);
        gunBulletsOverlay.translate(this.deltaGunBullets * 10 == -gunBulletsOverlay.getWidth() ? this.deltaGunBullets * 10 + 1 : this.deltaGunBullets * 10 / 2, 0);


    }
}
