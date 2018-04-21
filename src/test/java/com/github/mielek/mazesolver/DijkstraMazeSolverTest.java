package com.github.mielek.mazesolver;

public class DijkstraMazeSolverTest extends AbstractMazeSolverTest {

    @Override
    protected MazeSolver provideSolver(Maze maze) {
        return new DijkstraMazeSolver(maze);
    }

}
