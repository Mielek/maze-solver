package com.github.mielek.mazesolver;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class SimpleMazeSolverTest {
    @Test
    public void solveMazeWithOneLineCorridorInXAxis(){
        int[][] board = new int[][]{{0},{0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(1, 0);
        MazePoint[] expectedPath = new MazePoint[]{start, target};

        solveMazeAndCheckExpectedPath(board,start,target,expectedPath);
    }

    @Test
    public void startAndTargetAtTheSamePoint(){
        int[][] board = new int[][]{{0},{0}};
        MazePoint start = new MazePoint(0,0);
        MazePoint target = start;
        MazePoint[] expectedPath = new MazePoint[]{start};

        solveMazeAndCheckExpectedPath(board, start, target, expectedPath);
    }

    @Test
    public void solveMazeWithOneLineCorridorInYAxis(){
        int[][] board = new int[][]{{0, 0}};
        MazePoint start = new MazePoint(0,0);
        MazePoint target = new MazePoint(0,1);
        MazePoint[] expectedPath = new MazePoint[]{start, target};

        solveMazeAndCheckExpectedPath(board, start, target, expectedPath);
    }

    private void solveMazeAndCheckExpectedPath(int[][] board, MazePoint start, MazePoint target, MazePoint[] expectedPath) {
        Maze maze = Maze.builder()
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotNull().containsExactly(expectedPath);
    }
}
