package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import src.io.codeforall.fanstatics.entities.Player;

public class Door implements Interactable {
    private BoxCollider boxCollider;
    private BoxCollider interactionCollider;
    private boolean isOpen;
    private final int price;
    private Picture sprite;
    private Picture doorTip;

    public Door(int x, int y, Rooms room) {
        this.sprite = new Picture(x, y, room.getDoorSpriteFilePath());
        this.doorTip = new Picture(x + this.sprite.getWidth() / 2 - 150, y - 300, room.getDoorTipFilePath());

        this.boxCollider = new BoxCollider(x + 100, y + 100, this.sprite.getWidth() + 20, this.sprite.getHeight() + 20);
        this.interactionCollider = new BoxCollider(x - 300, y - 300, this.sprite.getWidth() + 600, this.sprite.getHeight() + 600);

        this.isOpen = false;
        this.price = room.getDoorPrice();
    }

    public void draw() {
        this.sprite.draw();
    }
    public void open() {
        this.isOpen = true;
        this.sprite.delete();
        this.doorTip.delete();
    }

    public void translate(int translateX, int translateY) {
        this.sprite.translate(translateX, translateY);
        this.doorTip.translate(translateX, translateY);
        this.boxCollider.bounds.translate(translateX, translateY);
        this.interactionCollider.bounds.translate(translateX, translateY);
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isOpened() {
        return this.isOpen;
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
    public void interact(Player player) {
        System.out.println("INTERACTING");
        this.open();
    }

    @Override
    public void showTip() {
        this.doorTip.draw();
    }

    @Override
    public void hideTip() {
        this.doorTip.delete();
    }

    @Override
    public BoxCollider getInteractionCollider() {
        return this.interactionCollider;
    }
}
