package src.io.codeforall.fanstatics.entities;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class PlayerController extends EntityController{

    Rectangle sprite;

    public PlayerController(Rectangle sprite) {
        this.sprite = sprite;

        this.sprite.setColor(Color.BLUE);
        this.sprite.fill();
    }

    public void init() {
    }

    @Override
    public void move(int[] translate) {

    }
}
