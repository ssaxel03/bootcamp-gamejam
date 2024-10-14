package src.io.codeforall.fanstatics;

public interface Collideable {

    public BoxCollider getBoxCollider();
    public void onCollision(Collideable col);



}
