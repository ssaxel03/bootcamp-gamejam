package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class EnemyController extends EntityController{

    private Rectangle sprite;

    public EnemyController(Rectangle sprite) {
        this.sprite = new Rectangle(0, 0, 100, 100);
        this.sprite.setColor(Color.RED);
        this.sprite.fill();
    }

    public Rectangle getSprite() {
        return this.sprite;
    }

    @Override
    public void move(int[] translate) {
        this.sprite.translate(translate[0], translate[1]);
    }
}
