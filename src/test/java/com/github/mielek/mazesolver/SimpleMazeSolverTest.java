package com.github.mielek.mazesolver;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SimpleMazeSolverTest {
    @Test
    public void oneLineMaze(){
        Maze maze = Maze.builder()
                .setBoard(new int[][]{{0},{0}})
                .setStart(new MazePoint(0, 0))
                .setTarget(new MazePoint(1, 0))
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty()
                .containsExactly(MazePoint.of(0, 0), MazePoint.of(1, 0));
    }

    @Test
    public void startAndTargetAtTheSamePoint(){
        MazePoint startTargetPoint = new MazePoint(0,0);
        Maze maze = Maze.builder()
                .setBoard(new int[][]{{0}})
                .setStart(startTargetPoint)
                .setTarget(startTargetPoint)
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotNull().containsExactly(startTargetPoint);
    }
}
