package jsh.boggle.view;

/**
 * @author Joël Hoekstra
 */
public class Position2D {

    private double x;
    private double y;

    public Position2D() {
        this(0.0, 0.0);
    }

    public Position2D(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public Position2D(Position2D position) {
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
