package src.io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import src.io.codeforall.fanstatics.entities.Player;

public class Box implements Interactable{
    private final String horizontalBoxFilePath = "assets/horizontalBox.png";
    private final String verticalBoxFilePath = "assets/verticalBox.png";
    private final String horizontalBoxOpenedFilePath = "assets/horizontalBoxOpened.png";
    private final String verticalBoxOpenedFilePath = "assets/verticalBoxOpened.png";
    private Picture sprite;
    private Picture openedSprite;

    private String boxTipFilePath = "assets/tipBox700.png";
    private Picture boxTip;
    private BoxCollider boxCollider;
    private BoxCollider interactionCollider;
    private boolean isOpen;
    private int price;
    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public Box(int x, int y, BoxType boxType) {
        switch (boxType) {
            case VERTICAL -> {
                this.sprite = new Picture(x, y, verticalBoxFilePath);
                this.openedSprite = new Picture(x, y, verticalBoxOpenedFilePath);
            }
            case HORIZONTAL -> {
                this.sprite = new Picture(x, y, horizontalBoxFilePath);
                this.openedSprite = new Picture(x, y, horizontalBoxOpenedFilePath);
            }
        }
        this.boxTip = new Picture(x + this.sprite.getWidth() / 2 - 150, y - 300, boxTipFilePath);

        this.boxCollider = new BoxCollider(x + 100, y + 80, this.sprite.getWidth() + 50, this.sprite.getHeight() + 50);
        this.interactionCollider = new BoxCollider(x - 300, y - 300, this.sprite.getWidth() + 600, this.sprite.getHeight() + 600);

        this.isOpen = false;
        this.price = 700;
    }

    @Override
    public void onCollision(Collideable col) {

    }

    @Override
    public String getName() {
        return "Box";
    }

    @Override
    public void interact(Player player) {
        this.open(player);
    }

    private void open(Player player) {
        this.isOpen = true;
        int gunToGive = 1 + (int) Math.floor(Math.random() * 2);
        System.out.println(gunToGive);
        player.giveGun(GunType.values()[gunToGive]);
        System.out.println("PLAYER NOW HAS "+ player.getGunType().getName());
        this.sprite.delete();
        this.boxTip.delete();
    }

    @Override
    public void showTip() {
        this.boxTip.draw();
    }

    @Override
    public void hideTip() {
        this.boxTip.delete();
    }

    @Override
    public BoxCollider getInteractionCollider() {
        return this.interactionCollider;
    }

    @Override
    public void draw() {
        this.openedSprite.draw();
        this.sprite.draw();
    }

    public void drawOpened() {
        this.openedSprite.draw();
    }

    @Override
    public void translate(int translateX, int translateY) {
        this.sprite.translate(translateX, translateY);
        this.openedSprite.translate(translateX, translateY);
        this.boxTip.translate(translateX, translateY);
        this.boxCollider.getBounds().translate(translateX, translateY);
        this.interactionCollider.getBounds().translate(translateX, translateY);
    }

    @Override
    public boolean canCollide() {
        return true;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean isOpened() {
        return this.isOpen;
    }
}
