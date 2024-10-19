package src.io.codeforall.fanstatics;

public enum GunType {
    PISTOL("Pistol",17,900, 500, 12, "assets/pistolIcon.png"),
    RIFLE("Rifle",30,500, 300, 30, "assets/rifleIcon.png"),
    SHOTGUN("Shotgun",110,1200, 600, 6, "assets/shotgunIcon.png");

    private final String gunIconFilePath;
    private final String name;
    private final int damage;
    private final long fireRateDelayMs;
    private final long reloadTimeMs;
    private final int bullets;

    GunType(String name, int damage, long fireRateDelayMs, int reloadTimeMs, int bullets, String gunIconFilePath){
        this.name = name;
        this.damage = damage;
        this.fireRateDelayMs = fireRateDelayMs;
        this.reloadTimeMs = reloadTimeMs;
        this.bullets = bullets;
        this.gunIconFilePath = gunIconFilePath;

    }

    public String getName() {
        return name;
    }
    public int getDamage(){
        return this.damage;
    }
    public long getFireRateDelayMs(){
        return this.fireRateDelayMs;
    }
    public long getReloadTimeMs() {
        return reloadTimeMs;
    }
    public int getBullets(){
        return this.bullets;
    }

    // DEVELOP NEW GUNS TO BE BOUGHT BY THE PLAYER
    // AT LEAST 3 GUNS ARE NEEDED
    // IMPLEMENTATION IS NOT NEEDED BUT IS ACCEPTED

}
