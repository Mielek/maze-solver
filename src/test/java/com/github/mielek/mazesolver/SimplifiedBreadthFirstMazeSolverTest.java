package com.github.mielek.mazesolver;

public class SimplifiedBreadthFirstMazeSolverTest extends AbstractMazeSolverTest {
    @Override
    protected MazeSolver provideSolver(Maze maze) {
        return new SimplifiedBreadthFirstMazeSolver(maze);
    }
}
