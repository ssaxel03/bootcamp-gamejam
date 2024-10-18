package src.io.codeforall.fanstatics;

public enum Rooms {
    BASIC(500, "assets/basicDoorTip"),
    FINALE(800, "assets/finaleDoorTip.png");

    private int doorPrice;
    private String doorTipFilePath;

    Rooms(int doorPrice, String doorTipFilePath) {
        this.doorPrice = doorPrice;
        this.doorTipFilePath = doorTipFilePath;
    }

    public int getDoorPrice() {
        return doorPrice;
    }

    public String getDoorTipFilePath() {
        return this.doorTipFilePath;
    }
}
