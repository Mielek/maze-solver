package com.github.mielek.mazesolver;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SimpleMazeSolverTest {
    @Test
    public void oneLineMazeSolved(){
        Maze maze = Maze.builder()
                .setBoard(new int[][]{ new int[]{0}, new int[]{0}, new int[]{0}})
                .setStart(new MazePoint(0, 0))
                .setTarget(new MazePoint(2, 0))
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty()
                .containsExactly(MazePoint.of(0, 0), MazePoint.of(1, 0), MazePoint.of(2, 0));
    }
}
