package src.io.codeforall.fanstatics;

import java.util.ArrayList;

public class CollisionManager {

    public ArrayList<Collideable> collideables;

    public CollisionManager(ArrayList<Collideable> collideables) {
        this.collideables = collideables;
    }

    public void checkCollisions() {
        for(Collideable collideable : collideables) {
            for(Collideable collideableTarget : collideables) {

                if(collideable == collideableTarget) {
                    continue;
                }

                if(collideable.getBoxCollider().getBounds().intersects(collideableTarget.getBoxCollider().getBounds())) {
                    collideable.onCollision(collideableTarget);
                    collideableTarget.onCollision(collideable);
                }

            }
        }
    }

}
