package com.github.mielek.mazesolver;

import java.util.ArrayList;
import java.util.List;

public class SimpleMazeSolver extends MazeSolver {
    public SimpleMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        MazePath path = new MazePath();
        if (maze.getStart().equals(maze.getTarget())) {
            path.getPoints().add(maze.getStart());
        } else {
            MazePoint dimension = maze.getDimension();
            List<MazePoint> pathPoints = new ArrayList<>();
            if (dimension.getX() > dimension.getY()) {
                for (int x = 0; x < dimension.getX(); ++x) {
                    pathPoints.add(MazePoint.of(x, 0));
                }
            } else {
                for (int y = 0; y < dimension.getY(); ++y) {
                    pathPoints.add(MazePoint.of(0, y));
                }
            }
            path.setPoints(pathPoints);
        }
        return path;
    }
}
