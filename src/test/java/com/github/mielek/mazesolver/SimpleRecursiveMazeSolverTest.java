package com.github.mielek.mazesolver;

public class SimpleRecursiveMazeSolverTest extends AbstractMazeSolverTest {
    @Override
    protected MazeSolver provideSolver(Maze maze) {
        return new SimpleRecursiveMazeSolver(maze);
    }
}
