package src.io.codeforall.fanstatics;

import src.io.codeforall.fanstatics.background.Background;
import src.io.codeforall.fanstatics.entities.Enemy;
import src.io.codeforall.fanstatics.entities.Entity;
import src.io.codeforall.fanstatics.entities.Player;

import java.awt.*;
import java.util.ArrayList;

public class CollisionManager {

    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bulletsShot;
    private ArrayList<Interactable> interactables;
    private Background background;
    public Player player;

    public CollisionManager(Background background, Player player, ArrayList<Enemy> enemies, ArrayList<Bullet> bulletsShot, ArrayList<Interactable> interactables) {
        this.background = background;
        this.player = player;
        this.enemies = enemies;
        this.bulletsShot = bulletsShot;
        this.interactables = interactables;
    }

    public void checkCollisions() {
        // CHECK ENEMIES
        for (Enemy enemy : enemies) {
            // IF ENEMY IS ALIVE IT MOVES
            if(enemy.getHealth() <= 0) {
                continue;
            }
            enemy.move(this.player);
            // System.out.println("CHECKING COLLISIONS FOR " + this.player.getName() + " AND " + enemy.getName());
            // CHECK IF EACH ENEMY COLLIDED WITH THE PLAYER
            if (this.player.getBounds().intersects(enemy.getBounds())) {
                System.out.println("ENEMY COLLIDED WITH PLAYER");
                this.player.onCollision(enemy);
                enemy.onCollision(this.player);
            }
            // CREATE ARRAY OF BULLETS TO BE REMOVED
            ArrayList<Bullet> bulletsToBeRemoved = new ArrayList<Bullet>();
            // CHECK ENEMY HITS FROM BULLETS
            for(Bullet bullet : bulletsShot) {
                bullet.move();
                if(bullet.getBoxCollider().getBounds().intersects(enemy.getBounds())) {
                    enemy.onCollision(bullet);
                    bullet.onCollision(enemy);
                    bulletsToBeRemoved.add(bullet);
                }
                // System.out.println(enemy.getHealth());
                // IF BULLET HAS BEEN SPAWNED FOR ENOUGH TIME IT DESPAWNS
                if(bullet.checkDespawn()) {bulletsToBeRemoved.add(bullet);}
            }
            // REMOVES BULLETS TO BE REMOVED
            bulletsShot.removeAll(bulletsToBeRemoved);
        }

        for (Interactable interactable : interactables) {
           if(interactable.getInteractionCollider().getBounds().intersects(this.player.getBounds())) {
               System.out.println("COLLIDED WITH INTERACTION COLLIDER");
               interactable.showTip();
               if(player.getEKey() && player.getMoney() > interactable.getPrice()) {
                   System.out.println("PLAYER IS INTERACTING");
                   interactable.interact();
                   player.pay(interactable.getPrice());
               }
           } else {
               interactable.hideTip();
           }
        }
    }

    public boolean isInside(int clickPositionX, int clickPositionY) {

        Rectangle player = new Rectangle(clickPositionX, clickPositionY, Entity.SPRITE_SIZE, Entity.SPRITE_SIZE);

        return this.background.getBoxCollider().contains(player);

    }

}
