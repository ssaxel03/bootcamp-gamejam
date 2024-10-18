package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Door implements Interactable {
    private BoxCollider boxCollider;
    private BoxCollider interactionCollider;
    private boolean isOpen;
    private final int price;
    private Picture sprite;
    private String doorTipFilePath;
    private final String spriteFilePath = "assets/door.png";


    public Door(int x, int y, Rooms room) {
        this.sprite = new Picture(x, y, spriteFilePath);
        this.boxCollider = new BoxCollider(x- 10, y - 10, this.sprite.getWidth() + 20, this.sprite.getHeight() + 20);
        this.interactionCollider = new BoxCollider(x - 100, y - 100, this.sprite.getWidth() + 200, this.sprite.getHeight() + 200);
        this.isOpen = false;
        this.price = room.getDoorPrice();
        this.doorTipFilePath = room.getDoorTipFilePath();
    }

    public void draw() {
        this.sprite.draw();
    }
    public void open() {
        this.isOpen = true;
        this.sprite.delete();
    }

    public void translate(int translateX, int translateY) {
        this.sprite.translate(translateX, translateY);
        this.boxCollider.bounds.translate(translateX, translateY);
        this.interactionCollider.bounds.translate(translateX, translateY);
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean canCollide() {
        return !this.isOpen;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void onCollision(Collideable col) {

    }

    @Override
    public String getName() {
        return "Door";
    }

    @Override
    public void interact() {
        System.out.println("INTERACTING");
        this.open();
    }

    @Override
    public void showTip() {

    }

    @Override
    public void hideTip() {

    }

    @Override
    public BoxCollider getInteractionCollider() {
        return this.interactionCollider;
    }
}
