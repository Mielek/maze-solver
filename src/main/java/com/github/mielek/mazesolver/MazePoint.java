package com.github.mielek.mazesolver;

import java.util.Objects;

/**
 * Simple point class.
 */
public class MazePoint {

    private int x;
    private int y;

    public MazePoint(){
        // x and why will be initialized with 0 value
    }

    public MazePoint(int x, int y){
        this.x=x;
        this.y=y;
    }

    public MazePoint(MazePoint cpy) {
        this.x = cpy.x;
        this.y = cpy.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Transforms current point to new one. Provided values are added and new instance of object is created.
     * @param x is x axis transform part
     * @param y is y axis transform part
     * @return new instance of {@code MazePoint} with transformed values
     */
    public MazePoint transform(int x, int y) {
        return of(this.x + x, this.y + y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazePoint that = (MazePoint) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" + x + "," + y + '}';
    }

    /**
     * Creates new instance with provided values.
     * @param x value of point
     * @param y value of point
     * @return new instance of {@code MazePoint}
     */
    public static MazePoint of(int x, int y) {
        return new MazePoint(x, y);
    }

}
