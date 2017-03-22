package jsh.boggle.view;

/**
 * Position object which only stores x and y axis. Thus 2D.
 *
 * Implementing this like new Position2D() and this will create a zero based coords Position2D object
 * Implementing this by calling new Position2D(double x, double y) will create a new Position2D object with the given x and y coords
 * Implementing this by calling new Position2D(Position2D position) will create the same object as Position2D(x, y)
 *
 * @author Joël Hoekstra
 */
public class Position2D {

    private double x = 0.0;
    private double y = 0.0;

    public Position2D() { }

    public Position2D(double x, double y) {
        this.setX(x).setY(y);
    }

    public Position2D(Position2D position) {
        this.setX(position.getX()).setY(position.getY());
    }

    public double getX() {
        return x;
    }

    public Position2D setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Position2D setY(double y) {
        this.y = y;
        return this;
    }

}
