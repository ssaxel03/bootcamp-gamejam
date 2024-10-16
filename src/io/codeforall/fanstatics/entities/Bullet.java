package src.io.codeforall.fanstatics.entities;

public class Bullet {

    private int damage;          // Damage dealt by the bullet
    private double x;            // Current x position
    private double y;            // Current y position
    private double speed;        // Speed of the bullet
    private double angle;        // Direction in which the bullet moves (in degrees)

    // Constructor
    public Bullet(int damage, double x, double y, double speed, double angle) {
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = Math.toRadians(angle); // Convert angle to radians for calculation
    }

    // Method to move the bullet
    public void move() {
        // Calculate new position based on speed and angle
        x += speed * Math.cos(angle);
        y -= speed * Math.sin(angle);
        System.out.println("Bullet moved to position: (" + x + ", " + y + ")");
    }

    // Method to check for collision with a target rectangle
    public boolean checkCollision(double targetX, double targetY, double targetWidth, double targetHeight) {
        return (x >= targetX && x <= targetX + targetWidth) &&
                (y >= targetY && y <= targetY + targetHeight);
    }

    // Getters
    public int getDamage() {
        return damage;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
