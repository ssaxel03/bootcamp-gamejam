package src.io.codeforall.fanstatics;

public interface Interactable extends Collideable {

    public void interact();
    public void showTip();
    public void hideTip();
    public BoxCollider getInteractionCollider();
    public void draw();
    public void translate(int directionX, int directionY);
    public boolean canCollide();
    public int getPrice();

}
