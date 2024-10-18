package src.io.codeforall.fanstatics.background;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import src.io.codeforall.fanstatics.*;
import src.io.codeforall.fanstatics.entities.Enemy;
import src.io.codeforall.fanstatics.entities.Entity;
import src.io.codeforall.fanstatics.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background {

    private Polygon boxCollider;
    private Picture spriteRoom0;
    private Picture spriteRoom1;
    private Picture spriteRoom2;
    private Door spriteDoor1;
    private final String room0SpriteFilePath = "assets/room0.png";
    private final String room1SpriteFilePath = "assets/room1.png";
    private final String room2SpriteFilePath = "assets/room2.png";

    private int[] offSet;

    private ArrayList<Enemy> enemies;
    private ArrayList<Interactable> interactables;
    private Player player;

    public Background(ArrayList<Enemy> enemies, ArrayList<Interactable> interactables) {
        this.boxCollider = new Polygon(
                new int[]{65, 1490, 1490, 1860, 1860, 4100, 4100, 3400, 3400, 4400, 4400, 260, 260, 2730, 2730, 1860, 1860, 1500, 1500, 65},
                new int[]{2000, 2000, 1210, 1210, 2000, 2000, -420, -420, -800, -800, -2430, -2430, -800, -800, -430, -430, 570, 570, 70, 70},
                20);

        this.offSet = new int[] {0, 0};

        this.spriteRoom0 = new Picture(0, 0, room0SpriteFilePath);
        this.spriteRoom0.draw();
        this.spriteRoom1 = new Picture(1680, -630, room1SpriteFilePath);
        this.spriteRoom1.draw();
        this.spriteRoom2 = new Picture(197, -2504, room2SpriteFilePath);
        this.spriteRoom2.draw();

        interactables.add(new Door(1627, 525, Rooms.BASIC));
        for (Interactable interactable : interactables) {
            interactable.draw();
        }

        // this.sprite.setColor(Color.DARK_GRAY);
        // this.sprite.fill();

        this.enemies = enemies;
        this.interactables = interactables;

    }

    public void moveTo() {
        // GET DIRECTION OF MOVEMENT FROM PLAYER
        int directionX = this.player.getADir() - this.player.getDDir();
        int directionY = this.player.getWDir() - this.player.getSDir();
        // CALCULATE DISTANCE TO BE TRAVELED
        float vectorLength = (float) Math.sqrt( (directionX * directionX) +
                (directionY * directionY) );
        // IF THE PLAYER DOESN'T WANT TO MOVE STOP
        if(vectorLength == 0) {
            return;
        }
        // GET DIRECTIONS BASED ON NORMALIZED VECTOR TIMES THE PLAYER SPEED
        try {
            directionX = (int) ((directionX * this.player.getSpeed()) / vectorLength);
            directionY = (int) ((directionY * this.player.getSpeed()) / vectorLength);
        } catch(Exception e) {
            // MAINLY FOR CATCHING DIVISIONS BY 0
            System.out.println(e.getMessage());
        }

        this.boxCollider.translate(directionX, directionY);
        this.translateInteractables(directionX, directionY);

        if(!this.boxCollider.contains(this.player.getBounds())) {
            this.boxCollider.translate(-directionX, -directionY);
            this.translateInteractables(-directionX, -directionY);
            return;
        }

        for (Interactable interactable : interactables) {
            if(interactable.getBoxCollider().getBounds().intersects(this.player.getBounds()) && interactable.canCollide()) {
                this.boxCollider.translate(-directionX, -directionY);
                this.translateInteractables(-directionX, -directionY);
                return;
            }
        }
        // MOVES THE BACKGROUND IN THE INVERTED DIRECTION THE PLAYER WANTS TO MOVE SO IT LOOKS LIKE THE CAMERA IS MOVING
        this.move(new int[] {directionX, directionY});
        this.offSet = new int[]{offSet[0] - directionX, offSet[1] - directionY};
    }

    private void translateInteractables(int directionX, int directionY) {
        for (Interactable interactable : interactables) {
            interactable.translate(directionX, directionY);
        }
    }

    public void move(int[] translate) {
        // MOVE THE ROOMS
        this.spriteRoom0.translate(translate[0], translate[1]);
        this.spriteRoom1.translate(translate[0], translate[1]);
        this.spriteRoom2.translate(translate[0], translate[1]);
        // MOVE THE BACKGROUND BOX COLLIDER
        // this.boxCollider.translate(translate[0], translate[1]);
        // MOVES EACH ENEMY
        ArrayList<Enemy> enemiesToBeRemoved = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if(enemy.isDespawnable() && enemy.isDead()) {
                enemy.deleteSprite();
                enemiesToBeRemoved.add(enemy);
            }
            enemy.move(translate);
        }
        enemies.removeAll(enemiesToBeRemoved);
    }

    public int getOffSetX() {
        return this.offSet[0];
    }

    public int getOffSetY() {
        return this.offSet[1];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Polygon getBoxCollider() {
        return this.boxCollider;
    }

    public void onCollision(Collideable col) {

    }

    public String getName() {
        return "Background";
    }
}
