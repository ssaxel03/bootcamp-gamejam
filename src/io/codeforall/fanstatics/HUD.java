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
        rifleIcon.draw();
        shotgunIcon = new Picture(265, 185, shotgunIconFilePath);
        shotgunIcon.draw();
        pistolIcon.draw();

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
        this.deltaHealth = 0;
        this.lastHealth = player.getHealth();
        // MONEY
        this.deltaMoney = 0;
        this.lastMoney = player.getMoney();
        // PLAYER BULLETS
        this.deltaPlayerBullets = 0;
        this.lastPlayerBullets = player.getBullets();
        // GUN BULLETS
        this.deltaGunBullets = 0;
        this.lastGunBullets = player.getGunBullets();
    }

    public void show() {

        switch(player.getGunType()) {
            case PISTOL:
                this.pistolIcon.delete();
                this.pistolIcon.draw();
                break;
            case RIFLE:
                this.rifleIcon.delete();
                this.rifleIcon.draw();
                break;
            case SHOTGUN:
                this.shotgunIcon.delete();
                this.shotgunIcon.draw();
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

        int changeHealth = deltaHealth * 3 / 2;
        if(changeHealth >= 300 - playerHealthOverlay.getWidth() / 2) {
            changeHealth = (300 - playerHealthOverlay.getWidth()) / 2;
        }
        if(changeHealth * 3 <= -playerHealthOverlay.getWidth() / 2) {
            changeHealth = -playerHealthOverlay.getWidth() / 2 + 1;
        }

        playerHealthOverlay.grow(changeHealth, 0);
        playerHealthOverlay.translate(changeHealth, 0);

        int changeMoney = (int) (this.deltaMoney / 3.33 / 2);
        if(changeMoney >= 300 - playerMoneyOverlay.getWidth() / 2) {
            changeMoney = (300 - playerMoneyOverlay.getWidth()) / 2;
        }
        if(changeMoney <= -playerMoneyOverlay.getWidth() / 2) {
            changeMoney = (-playerMoneyOverlay.getWidth() / 2) + 1;
        }

        playerMoneyOverlay.grow(changeMoney, 0);
        playerMoneyOverlay.translate(changeMoney, 0);

        int changePlayerBullets = (int) (this.deltaPlayerBullets * 1.7 / 2);
        if(changePlayerBullets >= 100 - playerBulletsOverlay.getWidth() / 2) {
            changePlayerBullets = (100 - playerBulletsOverlay.getWidth()) / 2;
        }
        if(changePlayerBullets <= -playerBulletsOverlay.getWidth() / 2) {
            changePlayerBullets = -playerBulletsOverlay.getWidth() / 2 + 1;
        }

        playerBulletsOverlay.grow(changePlayerBullets, 0);
        playerMoneyOverlay.translate(changePlayerBullets, 0);

        int changeGunBullets = (int) (this.deltaGunBullets * 3.33 / 2);
        if(changeGunBullets >= 100 - gunBulletsOverlay.getWidth() / 2) {
            changeGunBullets = (100 - gunBulletsOverlay.getWidth() / 2);
        }
        if(changeGunBullets <= -gunBulletsOverlay.getWidth() / 2) {
            changeGunBullets = -gunBulletsOverlay.getWidth() / 2 + 1;
        }

        gunBulletsOverlay.grow(changeGunBullets, 0);
        gunBulletsOverlay.translate(changeGunBullets, 0);

        playerHealthOverlay.delete();
        playerHealthOverlay.draw();
        playerHealthFrame.delete();
        playerHealthFrame.draw();

        playerMoneyOverlay.delete();
        playerMoneyOverlay.draw();
        playerMoneyFrame.delete();
        playerMoneyFrame.draw();

        playerBulletsOverlay.delete();
        playerBulletsOverlay.draw();
        playerBulletsFrame.delete();
        playerBulletsFrame.draw();

        gunBulletsOverlay.delete();
        gunBulletsOverlay.draw();
        gunBulletsFrame.delete();
        gunBulletsFrame.draw();

        playerFace.delete();
        playerFace.draw();
    }
}
