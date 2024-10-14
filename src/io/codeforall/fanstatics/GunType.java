package src.io.codeforall.fanstatics;

public enum GunType {
    PISTOL(5,800,10),
    RIFLE(10,100,15),
    SHOTGUN(15,400,6);

    private int damage;
    private long delayMs;
    private int bullets;

    GunType(int damage, long delayMs, int bullets){
        this.damage = damage;
        this.delayMs = delayMs;
        this.bullets = bullets;
    }

    public int getDamage(){
        return this.damage;
    }

    public long getDelayMs(){
        return this.delayMs;
    }
    public int getBullets(){
        return this.bullets;
    }

    // DEVELOP NEW GUNS TO BE BOUGHT BY THE PLAYER
    // AT LEAST 3 GUNS ARE NEEDED
    // IMPLEMENTATION IS NOT NEEDED BUT IS ACCEPTED

}
