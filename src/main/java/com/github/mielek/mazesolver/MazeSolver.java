package com.github.mielek.mazesolver;

/**
 * Base class for maze solvers.
 */
public abstract class MazeSolver {
    protected Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    /**
     * Solves maze.
     * @return path from start to target
     */
    public abstract MazePath solve();
}
