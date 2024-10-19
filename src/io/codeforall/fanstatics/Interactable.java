package src.io.codeforall.fanstatics;

import src.io.codeforall.fanstatics.entities.Player;

public interface Interactable extends Collideable {

    public void interact(Player player);
    public void showTip();
    public void hideTip();
    public BoxCollider getInteractionCollider();
    public void draw();
    public void translate(int translateX, int translateY);
    public boolean canCollide();
    public int getPrice();
    public boolean isOpened();

}
