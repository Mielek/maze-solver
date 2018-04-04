package com.github.mielek.mazesolver;

import java.util.*;

public class SimpleMazeSolver extends MazeSolver {

    List<MazePoint> path = null;
    Set<MazePoint> checked = new HashSet<>();

    public SimpleMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        if(path==null){
            path = new ArrayList<>();
            if (maze.getStart().equals(maze.getTarget())) {
                path.add(maze.getStart());
            } else {
                findTarget(maze.getStart());
                Collections.reverse(path);
            }
        }
        return new MazePath(path);
    }

    private boolean findTarget(MazePoint point) {
        if (isOutOfBounds(point))
            return false;

        if(!checked.add(point) || isWall(point))
            return false;

        if(isTarget(point) || goUp(point) || goDown(point) || goLeft(point) || goRight(point)) {
            path.add(point);
            return true;
        }

        return false;
    }

    private boolean isWall(MazePoint point) {
        return maze.getBoard()[point.getX()][point.getY()] == Maze.WALL;
    }

    private boolean isOutOfBounds(MazePoint point) {
        if (point.getX() < 0 || point.getX() >= maze.getDimension().getX()) {
            return true;
        }
        if (point.getY() < 0 || point.getY() >= maze.getDimension().getY()) {
            return true;
        }
        return false;
    }

    private boolean isTarget(MazePoint point){
        return maze.getTarget().equals(point);
    }

    private boolean goUp(MazePoint point) {
        return findTarget(point.transform(0, -1));
    }

    private boolean goDown(MazePoint point) {
        return findTarget(point.transform(0, 1));
    }

    private boolean goLeft(MazePoint point) {
        return findTarget(point.transform(-1, 0));
    }

    private boolean goRight(MazePoint point) {
        return findTarget(point.transform(1, 0));
    }
}
