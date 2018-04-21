package com.github.mielek.mazesolver;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ShortestPathMazeSolverTest extends AbstractMazeSolverTest {

    @Test
    public void pathFoundInMazeIsShortest() {
        String stringBoard =
                "********\n" +
                        "*TXXXXXX\n" +
                        "*X*****X\n" +
                        "*X*XXXXS\n" +
                        "*XXX****\n" +
                        "********";
        int[][] board = createBoardFromString(stringBoard);
        MazePoint dimension = MazePoint.of(board.length, board[0].length);

        MazePoint start = MazePoint.of(1, 1);
        MazePoint target = MazePoint.of(7, 3);
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
                .doesNotContainAnyElementsOf(createWallListFromMazeBoard(board)).hasSize(9);
        isPathConsistent(path);
    }

}
