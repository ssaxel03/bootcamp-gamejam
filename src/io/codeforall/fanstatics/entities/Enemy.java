package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.Bullet;
import src.io.codeforall.fanstatics.Collideable;
import src.io.codeforall.fanstatics.Vectors;

public class Enemy extends Entity implements Collideable {

    private final EnemyController enemyController;
    private int[] position;
    private long deletionTimerMs;
    private long despawnTimeMs = 1500;

    public Enemy(int x, int y) {
        super(x, y, 7, "Enemy");
        this.enemyController = new EnemyController(
                new Rectangle(0, 0,
                        Entity.SPRITE_SIZE,
                        Entity.SPRITE_SIZE)
        );
        this.position = new int[]{0, 0};
    }

    public void move(int[] translate) {
        this.enemyController.move(translate);
        this.boxCollider.move(translate);
        this.position = new int[]{position[0] + translate[0], position[1] + translate[1]};
    }

    public void move(Player player) {
        // ATTRIBUTE DIRECTION OF ENEMY MOVEMENT
        int directionX = player.getPosition()[0] - this.position[0];
        int directionY = player.getPosition()[1] - this.position[1];

        // DEBUG
        // System.out.println("DIRECTION X " + directionX);
        // System.out.println("DIRECTION y " + directionY);

        // CHECKS IF WE ARE CLOSE ENOUGH TO THE PLAYER SO THAT OUR NORMALIZED VECTOR TIMES SPEED ARE GOING TO SURPASS THE GOAL POSITION
        if (Vectors.getVectorLength(directionX, directionY) < super.speed) {
            // MOVES JUST THE AMOUNT NEEDED TO REACH THE PLAYER
            this.move(new int[]{directionX, directionY});
        } else {
            // MOVES THE MAXIMUM AMOUNT ALLOWED BY THE SPEED ATTRIBUTE OF THE ENEMY
            float[] normalizedArray = Vectors.getNormalizedDirection(directionX, directionY);

            // DEBUG
            // System.out.println("NORMALIZED ARRAY X " + normalizedArray[0] + " Y " + normalizedArray[1]);

            // CREATES THE MOVE ARRAY ACCORDING TO THE ENEMY SPEED
            int[] moveArray = new int[]{(int) (normalizedArray[0] * super.speed), (int) (normalizedArray[1] * super.speed)};

            // DEBUG
            // System.out.println("MOVE ARRAY X " + moveArray[0] + " Y " + moveArray[1]);

            // MOVES THE ENEMY
            this.move(moveArray);
        }
    }

    @Override
    public void onCollision(Collideable col) {
        // ENEMY COLLISION METHOD
        // DEBUG
        // System.out.println("Enemy collided with " + col.getName());
        switch(col.getName()) {
            case "Bullet" -> this.hit(((Bullet) col).getDamage());
        }

        if (this.health <= 0) {
            this.die();
        }
    }

    private void die() {
        this.isDead = true;
        this.enemyController.getSprite().setColor(Color.BLACK);
        this.deletionTimerMs = System.currentTimeMillis();
    }

    public void deleteSprite() {
        this.enemyController.getSprite().delete();
    }

    public boolean isDespawnable() {
        return System.currentTimeMillis() - deletionTimerMs >= despawnTimeMs;
    }

    public int getPositionX() {
        return this.position[0];
    }

    public int getPositionY() {
        return this.position[1];
    }

    public int getHealth() {
        return this.health;
    }
}
