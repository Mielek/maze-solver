package com.github.mielek.mazesolver;

public abstract class MazeSolver {
    private Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public abstract MazePath solve();
}
