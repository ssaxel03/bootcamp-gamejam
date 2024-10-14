package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import src.io.codeforall.fanstatics.Collideable;

import java.util.Vector;

public class Enemy extends Entity{

    private final EnemyController enemyController;
    private int[] position;

    public Enemy(int x, int y) {
        super(x, y, 5);
        this.enemyController = new EnemyController(
                new Rectangle(10, 10,
                        Entity.SPRITE_SIZE,
                        Entity.SPRITE_SIZE)
        );
        this.position = new int[] {10, 10};
    }

    public void move(int[] translate) {
        enemyController.move(translate);
        this.position = new int[] { position[0] + translate[0], position[1] + translate[1] };
    }

    public void move(Player player) {



        int[] direction = new int[] {10 + player.getPosition()[0] - this.position[0], 10 + player.getPosition()[1] - this.position[1]};

        double arrayLength = Math.sqrt( (direction[0] * direction[0]) + (direction[1] * direction[1]) );

        if(arrayLength < 150) {
            return;
        }

        int[] normalizedDirection = new int[] {(int) (super.speed * direction[0] / arrayLength), (int) (this.speed * direction[1] / arrayLength)};

        this.enemyController.move(normalizedDirection);

        // this.enemyController.move(new int[] {100, 0});

        this.position = new int[] {this.position[0] + normalizedDirection[0], this.position[1] + normalizedDirection[1] };

    }

    @Override
    public void onCollision(Collideable col) {
        // ENEMY COLLISION METHOD
        return;
    }
}
