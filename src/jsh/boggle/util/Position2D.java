package jsh.boggle.util;

/**
 * Position object which only stores x and y axis. Thus 2D.
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
        return "Position2D<" + x.getClass() + ">: x(" + x + ") y(" + y + ")";
    }

}
