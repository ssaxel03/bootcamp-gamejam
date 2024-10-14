package src.io.codeforall.fanstatics;

public enum Boosts {

    HEALTH_BOOST(20),
    SPEED(2),
    ARMOR(50),
    BULLETS(5),
    RELOADTIME(0.5);

    private double value;

    Boosts(double value){
        this.value=value;
    }


    // DEVELOP MEW BOOSTS TO BE DROPPED FROM ENEMIES
    // ENUMERATOR SHOULD CONTAIN AT LEAST 3 BOOSTS
    // IMPLEMENTATION IS NOT NEEDED BUT IS NICE

}
