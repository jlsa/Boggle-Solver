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
public class Position2D<E> {

    private E x = null;
    private E y = null;

    public Position2D() { }

    public Position2D(E x, E y) {
        this.setX(x).setY(y);
    }

    public E getX() {
        return x;
    }

    public Position2D setX(E x) {
        this.x = x;
        return this;
    }

    public E getY() {
        return y;
    }

    public Position2D setY(E y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "Position2D : x(" + x + ") y(" + y + ")";
    }

}
