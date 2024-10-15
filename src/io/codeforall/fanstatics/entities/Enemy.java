package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.Collideable;

public class Enemy extends Entity implements Collideable{

    private final EnemyController enemyController;
    private int[] position;

    public Enemy(int x, int y) {
        super(x, y, 3, "Enemy");
        this.enemyController = new EnemyController(
                new Rectangle(0, 0,
                        Entity.SPRITE_SIZE,
                        Entity.SPRITE_SIZE)
        );
        this.position = new int[] {0, 0};
    }

    public void move(int[] translate) {
        this.enemyController.move(translate);
        this.boxCollider.move(translate);
        this.position = new int[] { position[0] + translate[0], position[1] + translate[1] };
    }

    public void move(Player player) {

        // CALCULATE DIRECTION VECTOR TO PLAYER
        int[] direction = new int[] {player.getPosition()[0] - this.position[0], player.getPosition()[1] - this.position[1]};
        // CALCULATE DISTANCE OF DIRECTION TO PLAYER
        double arrayLength = Math.sqrt( (direction[0] * direction[0]) + (direction[1] * direction[1]) );
        // CREATE NEW DIRECTION VECTOR ALWAYS WITH THE SAME SIZE
        int[] normalizedDirection = new int[] {(int) (super.speed * direction[0] / arrayLength), (int) (this.speed * direction[1] / arrayLength)};
        // MOVE ALL OF ENEMY'S COMPONENTS
        this.move(normalizedDirection);

    }

    @Override
    public void onCollision(Collideable col) {
        // ENEMY COLLISION METHOD

        System.out.println("Enemy collided with " + col.getName());

        return;
    }
}
