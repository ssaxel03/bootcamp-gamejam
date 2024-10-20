package src.io.codeforall.fanstatics;

public enum Rooms {
    BASIC(500, "assets/tipBox500.png", "assets/verticalDoor.png"),
    FINALE(800, "assets/tipBox800.png", "assets/horizontalDoor.png");

    private int doorPrice;
    private String doorTipFilePath;
    private String doorSpriteFilePath;

    Rooms(int doorPrice, String doorTipFilePath, String doorSpriteFilePath) {
        this.doorPrice = doorPrice;
        this.doorTipFilePath = doorTipFilePath;
        this.doorSpriteFilePath = doorSpriteFilePath;
    }

    public int getDoorPrice() {
        return doorPrice;
    }

    public String getDoorTipFilePath() {
        return this.doorTipFilePath;
    }
    public String getDoorSpriteFilePath() {
        return this.doorSpriteFilePath;
    }
}
