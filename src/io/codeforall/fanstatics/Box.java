package src.io.codeforall.fanstatics;

public class Box implements Interactable{
    private final String horizontalBoxFilePath = "assets/horizontalBox.png";
    private final String verticalBoxFilePath = "assets/verticalBox.png";

    private BoxCollider boxCollider;
    private BoxCollider interactionCollider;
    private boolean isOpen;
    private int price;

    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }

    @Override
    public void onCollision(Collideable col) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void interact() {

    }

    @Override
    public void showTip() {

    }

    @Override
    public void hideTip() {

    }

    @Override
    public BoxCollider getInteractionCollider() {
        return null;
    }

    @Override
    public void draw() {

    }

    @Override
    public void translate(int directionX, int directionY) {

    }

    @Override
    public boolean canCollide() {
        return false;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
