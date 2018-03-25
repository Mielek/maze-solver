package com.github.mielek.mazesolver;


import java.util.Arrays;

/**
 * Maze
 */
public class Maze {

    private int[][] board;
    private MazePoint start;
    private MazePoint target;

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


    public static class MazeBuilder{
        Maze mazeToBuild = new Maze();

        public MazeBuilder setBoard(int[][] board){
            mazeToBuild.board = board;
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
            result.start = new MazePoint(mazeToBuild.start);
            result.target = new MazePoint(mazeToBuild.target);
            return result;
        }

    }
}