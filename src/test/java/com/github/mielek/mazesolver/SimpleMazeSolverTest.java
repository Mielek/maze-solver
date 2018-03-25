package com.github.mielek.mazesolver;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class SimpleMazeSolverTest {
    @Test
    public void solveMazeWithOneLineCorridorInXAxisWithTwoPoints() {
        MazePoint dimension = MazePoint.of(2, 1);
        int[][] board = new int[dimension.getX()][dimension.getY()];
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(1, 0);
        MazePoint[] expectedPath = new MazePoint[]{start, target};

        solveMazeAndCheckExpectedPath(dimension, board, start, target, expectedPath);
    }

    @Test
    public void startAndTargetAtTheSamePoint() {
        MazePoint dimension = MazePoint.of(1, 1);
        int[][] board = new int[dimension.getX()][dimension.getY()];
        MazePoint start = new MazePoint(0, 0);
        MazePoint target = start;
        MazePoint[] expectedPath = new MazePoint[]{start};

        solveMazeAndCheckExpectedPath(dimension, board, start, target, expectedPath);
    }

    @Test
    public void solveMazeWithOneLineCorridorInYAxisWithTwoPoints() {
        MazePoint dimension = MazePoint.of(1, 2);
        int[][] board = new int[dimension.getX()][dimension.getY()];
        MazePoint start = new MazePoint(0, 0);
        MazePoint target = new MazePoint(0, 1);
        MazePoint[] expectedPath = new MazePoint[]{start, target};

        solveMazeAndCheckExpectedPath(dimension, board, start, target, expectedPath);
    }

    @Test
    public void solveMazeWithOneLineCorridorInXAxisWithLengthOf3() {
        MazePoint dimension = MazePoint.of(3, 1);
        int[][] board = new int[dimension.getX()][dimension.getY()];
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(2, 0);
        MazePoint[] expectedPath = new MazePoint[]{start, MazePoint.of(1, 0), target};

        solveMazeAndCheckExpectedPath(dimension, board, start, target, expectedPath);
    }

    @Test
    public void solveMazeWithOneLineCorridorInYAxisWithLengthOf3() {
        MazePoint dimension = MazePoint.of(1, 3);
        int[][] board = new int[dimension.getX()][dimension.getY()];
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(0, 2);
        MazePoint[] expectedPath = new MazePoint[]{start, MazePoint.of(0, 1), target};

        solveMazeAndCheckExpectedPath(dimension, board, start, target, expectedPath);
    }

    @Test
    public void solveMazeWithNoPathInOneLineCorridorInXAxisBecauseOfWall() {
        MazePoint dimension = MazePoint.of(3, 0);
        int[][] board = new int[][]{{0}, {1}, {0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(2, 0);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isEmpty();
    }

    private void solveMazeAndCheckExpectedPath(MazePoint dimension, int[][] board, MazePoint start, MazePoint target, MazePoint[] expectedPath) {
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        SimpleMazeSolver solver = new SimpleMazeSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty().containsExactly(expectedPath);
    }
}
