package src.io.codeforall.fanstatics;

import src.io.codeforall.fanstatics.background.Background;
import src.io.codeforall.fanstatics.entities.Enemy;
import src.io.codeforall.fanstatics.entities.Entity;
import src.io.codeforall.fanstatics.entities.Player;

import java.awt.*;
import java.util.ArrayList;

public class CollisionManager {

    public ArrayList<Enemy> enemies;
    private Background background;
    public Player player;

    public CollisionManager(ArrayList<Enemy> enemies, Background background, Player player) {
        this.enemies = enemies;
        this.background = background;
        this.player = player;
    }

    public void checkCollisions() {
        for (Enemy enemy : enemies) {

            // System.out.println("CHECKING COLLISIONS FOR " + this.player.getName() + " AND " + enemy.getName());

            if (this.player.getBoxCollider().getBounds().intersects(enemy.getBoxCollider().getBounds())) {
                this.player.onCollision(enemy);
                enemy.onCollision(this.player);
            }
        }
    }

    public boolean isInside(int[] clickPosition) {

        Rectangle player = new Rectangle(clickPosition[0], clickPosition[1], Entity.SPRITE_SIZE, Entity.SPRITE_SIZE);

        return this.background.getBoxCollider().bounds.contains(player);

    }

}
