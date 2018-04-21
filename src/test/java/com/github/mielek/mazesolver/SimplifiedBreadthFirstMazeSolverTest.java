package com.github.mielek.mazesolver;

public class SimplifiedBreadthFirstMazeSolverTest extends ShortestPathMazeSolverTest {

    @Override
    protected MazeSolver provideSolver(Maze maze) {
        return new SimplifiedBreadthFirstMazeSolver(maze);
    }

}
