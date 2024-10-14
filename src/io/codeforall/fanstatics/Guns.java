package src.io.codeforall.fanstatics;

public enum Guns {
    PISTOL(5,800),
    RIFLE(10,100),
    SHOTGUN(15,400);

    private int damage;
    private int delayMs;

    Guns(int damage, int delayMs){
        this.damage = damage;
        this.delayMs = delayMs;
    }

    public int getDamage(){
        return this.damage;
    }

    public long getDelayMs(){
        return this.delayMs;
    }

    // DEVELOP NEW GUNS TO BE BOUGHT BY THE PLAYER
    // AT LEAST 3 GUNS ARE NEEDED
    // IMPLEMENTATION IS NOT NEEDED BUT IS ACCEPTED

}
