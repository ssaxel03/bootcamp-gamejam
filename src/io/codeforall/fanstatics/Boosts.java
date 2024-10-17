package src.io.codeforall.fanstatics;

public enum Boosts {

    HEALTH_BOOST(20), //increase the health of the player
    SPEED_BOOST(2), //increase the speed of the player
    ARMOR_BOOST(30), //increase the armor points
    BULLETS_BOOST(5), // increase the number of bullets
    RELOAD_TIME_BOOST(500); //decrease the reload time

    private int value;

    Boosts(int value){
        this.value=value;
    }


    // DEVELOP NEW BOOSTS TO BE DROPPED FROM ENEMIES
    // ENUMERATOR SHOULD CONTAIN AT LEAST 3 BOOSTS
    // IMPLEMENTATION IS NOT NEEDED BUT IS NICE

}
