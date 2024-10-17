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
    private Background background;
    public Player player;

    public CollisionManager(Background background, Player player, ArrayList<Enemy> enemies, ArrayList<Bullet> bulletsShot) {
        this.background = background;
        this.player = player;
        this.enemies = enemies;
        this.bulletsShot = bulletsShot;
    }

    public void checkCollisions() {
        for (Enemy enemy : enemies) {
            if(enemy.getHealth() > 0) {
                enemy.move(this.player);
            }
            // System.out.println("CHECKING COLLISIONS FOR " + this.player.getName() + " AND " + enemy.getName());
            // CHECK IF EACH ENEMY COLLIDED WITH THE PLAYER
            if (this.player.getBoxCollider().getBounds().intersects(enemy.getBounds())) {
                this.player.onCollision(enemy);
                enemy.onCollision(this.player);
                this.player.onCollision(enemy);
            }

            ArrayList<Bullet> toBeRemoved = new ArrayList<Bullet>();
            for(Bullet bullet : bulletsShot) {
                bullet.move();
                if(bullet.getBoxCollider().getBounds().intersects(enemy.getBounds())) {
                    enemy.onCollision(bullet);
                    bullet.onCollision(enemy);
                    toBeRemoved.add(bullet);
                }
                if(bullet.checkDespawn()) {toBeRemoved.add(bullet);}
            }

            bulletsShot.removeAll(toBeRemoved);
        }
    }

    // DEPRECATED
    public boolean isInside(int[] clickPosition) {

        Rectangle player = new Rectangle(clickPosition[0], clickPosition[1], Entity.SPRITE_SIZE, Entity.SPRITE_SIZE);

        return this.background.getBoxCollider().bounds.contains(player);

    }

    public boolean isInside(int clickPositionX, int clickPositionY) {

        Rectangle player = new Rectangle(clickPositionX, clickPositionY, Entity.SPRITE_SIZE, Entity.SPRITE_SIZE);

        return this.background.getBoxCollider().bounds.contains(player);

    }

}
