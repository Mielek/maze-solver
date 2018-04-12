package com.github.mielek.mazesolver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public abstract class AbstractMazeSolverTest {

    /*  ##############
     *  # TEST CASES #
     *  ##############
     */

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
        MazePoint dimension = MazePoint.of(3, 1);
        int[][] board = new int[][]{{0}, {1}, {0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(2, 0);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isEmpty();
    }

    @Test
    public void solveMazeWithNoPathInOneLineCorridorInYAxisBecauseOfWall() {
        MazePoint dimension = MazePoint.of(1, 3);
        int[][] board = new int[][]{{0, 1, 0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(0, 2);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isEmpty();
    }

    @Test
    public void solveNoWallSquareMazeWithEndsOnDiagonal(){
        MazePoint dimension = MazePoint.of(3, 3);
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(2, 2);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty().doesNotHaveDuplicates().startsWith(start).endsWith(target);
        isPathConsistent(path);
    }

    @Test
    public void solveSquareMazeWithWallsInsideAndCorridorsOnBordersWithEndsOnDiagonal(){
        MazePoint dimension = MazePoint.of(4, 4);
        int[][] board = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        MazePoint start = MazePoint.of(0, 0);
        MazePoint target = MazePoint.of(3, 3);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty().doesNotHaveDuplicates().startsWith(start).endsWith(target)
                .doesNotContainAnyElementsOf(createWallListFromMazeBoard(board));
        isPathConsistent(path);
    }

    @Test
    public void solveSquareMazeWithWallsOnBordersAndCorridorInsideWithEndsOnDiagonal(){
        MazePoint dimension = MazePoint.of(4, 4);
        int[][] board = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        MazePoint start = MazePoint.of(1, 1);
        MazePoint target = MazePoint.of(2, 2);
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty().doesNotHaveDuplicates().startsWith(start).endsWith(target)
                .doesNotContainAnyElementsOf(createWallListFromMazeBoard(board));
        isPathConsistent(path);
    }

    /*  ###################
     *  # ABSTRACT METHOD #
     *  ###################
     */

    protected abstract MazeSolver provideSolver(Maze maze);

    /*  ##################
     *  # HELPER METHODS #
     *  ##################
     */

    protected List<MazePoint> createWallListFromMazeBoard(int[][] board){
        List<MazePoint> wallPoints = new ArrayList<>();
        for (int x = 0; x < board.length; ++x) {
            for (int y = 0; y < board[x].length; ++y) {
                if(board[x][y]>0)
                    wallPoints.add(MazePoint.of(x, y));
            }
        }
        return wallPoints;
    }

    protected void isPathConsistent(MazePath path) {
        for (int i = 0; i < path.getPoints().size()-1; ++i) {
            MazePoint current = path.getPoints().get(i);
            MazePoint next = path.getPoints().get(i+1);
            if (current.getX() + 1 == next.getX()) {
                assertThat(current.getY()).isEqualTo(next.getY()).withFailMessage("Path is not consistent in {} value", i);
            } else if (current.getY() + 1 == next.getY()) {
                assertThat(current.getX()).isEqualTo(current.getX()).withFailMessage("Path is not consistent in {} value", i);
            } else if (current.getX() - 1 == next.getX()) {
                assertThat(current.getY()).isEqualTo(next.getY()).withFailMessage("Path is not consistent in {} value", i);
            } else if (current.getY() - 1 == next.getY()) {
                assertThat(current.getX()).isEqualTo(current.getX()).withFailMessage("Path is not consistent in {} value", i);
            } else {
                fail("Path is not consistent. Out of test cases. Really bad. Really.");
            }
        }
    }

    protected void solveMazeAndCheckExpectedPath(MazePoint dimension, int[][] board, MazePoint start, MazePoint target, MazePoint[] expectedPath) {
        Maze maze = Maze.builder()
                .setDimension(dimension)
                .setBoard(board)
                .setStart(start)
                .setTarget(target)
                .build();
        MazeSolver solver = provideSolver(maze);

        MazePath path = solver.solve();

        assertThat(path).isNotNull();
        assertThat(path.getPoints()).isNotNull().isNotEmpty().startsWith(start).endsWith(target).containsExactly(expectedPath);
        isPathConsistent(path);
    }
}
