package src.io.codeforall.fanstatics;

public enum Guns {

    PISTOL("Pistol", 15, 1.2),
    RIFLE("Rifle", 30, 0.8),
    SHOTGUN("Shotgun", 40, 1.5),
    SNIPER("Sniper", 80, 2.5);


    private final String name;
    private final int damage;
    private final double reloadTime;

    Guns(String name, int damage, double reloadTime) {
        this.name = name;
        this.damage = damage;
        this.reloadTime = reloadTime;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    // DEVELOP NEW GUNS TO BE BOUGHT BY THE PLAYER
    // AT LEAST 3 GUNS ARE NEEDED
    // IMPLEMENTATION IS NOT NEEDED BUT IS ACCEPTED

}
