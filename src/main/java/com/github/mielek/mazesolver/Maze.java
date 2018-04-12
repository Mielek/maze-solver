package com.github.mielek.mazesolver;

/**
 * Maze
 */
public class Maze {

    public static final int CORRIDOR=0;
    public static final int WALL=1;

    private int[][] board;
    private MazePoint start;
    private MazePoint target;
    private MazePoint dimension;

    public static MazeBuilder builder() {
        return new MazeBuilder();
    }

    public MazePoint getTarget() {
        return target;
    }

    public MazePoint getStart() {
        return start;
    }

    public int[][] getBoard() {
        return board;
    }

    public MazePoint getDimension() {
        return dimension;
    }

    public boolean isOutOfBounds(MazePoint point) {
        if (point.getX() < 0 || point.getX() >= dimension.getX()) {
            return true;
        }
        if (point.getY() < 0 || point.getY() >= dimension.getY()) {
            return true;
        }
        return false;
    }

    public boolean isWall(MazePoint point) {
        return board[point.getX()][point.getY()] == Maze.WALL;
    }

    public boolean isStart(MazePoint point){
        return  start.equals(point);
    }

    public boolean isTarget(MazePoint point){
        return target.equals(point);
    }


    public static class MazeBuilder{
        Maze mazeToBuild = new Maze();

        public MazeBuilder setBoard(int[][] board){
            mazeToBuild.board = board;
            return this;
        }

        public MazeBuilder setDimension(MazePoint dimension){
            mazeToBuild.dimension = dimension;
            return this;
        }

        public MazeBuilder setStart(MazePoint start){
            mazeToBuild.start = start;
            return this;
        }

        public MazeBuilder setTarget(MazePoint target){
            mazeToBuild.target = target;
            return this;
        }

        public Maze build(){
            Maze result = new Maze();
            result.board = new int[mazeToBuild.board.length][mazeToBuild.board[0].length];
            for (int i = 0; i < result.board.length; ++i) {
                System.arraycopy(mazeToBuild.board[i],0,result.board[i],0, mazeToBuild.board[i].length);
            }
            result.dimension = new MazePoint(mazeToBuild.dimension);
            result.start = new MazePoint(mazeToBuild.start);
            result.target = new MazePoint(mazeToBuild.target);
            return result;
        }

    }
}