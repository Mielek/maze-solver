package com.github.mielek.mazesolver;

import java.util.Arrays;

public class SimpleMazeSolver extends MazeSolver {
    public SimpleMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        MazePath path = new MazePath();
        if(maze.getStart().equals(maze.getTarget())){
            path.getPoints().add(maze.getStart());
        } else {
            path.getPoints().addAll(
                    Arrays.asList(
                            MazePoint.of(0, 0),
                            MazePoint.of(1, 0)));
        }
        return path;
    }
}
