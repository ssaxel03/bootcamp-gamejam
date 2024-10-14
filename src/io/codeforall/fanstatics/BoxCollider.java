package src.io.codeforall.fanstatics;

import java.awt.*;

public class BoxCollider {

    Rectangle bounds;

    public BoxCollider(int x, int y, int width, int height) {

        this.bounds = new Rectangle(x, y, width, height);

    }

    public void move(int[] translate) {
        this.bounds.translate(translate[0], translate[1]);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

}
