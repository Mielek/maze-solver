package com.github.mielek.mazesolver;

import java.util.*;

/**
 * Simple maze solver. It uses simple recursive algorithm to find path in maze.
 */
public class SimpleRecursiveMazeSolver extends MazeSolver {

    List<MazePoint> path = null;
    Set<MazePoint> checked = new HashSet<>();

    public SimpleRecursiveMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        if(path==null){ // we do not need to solve maze again
            path = new ArrayList<>();
            if (maze.getStart().equals(maze.getTarget())) {
                path.add(maze.getStart());
            } else {
                findTarget(maze.getStart());
                Collections.reverse(path); // we need to reverse path because it was filled in reverse order
                checked = null; // not needed but it removes not needed references
            }
        }
        return new MazePath(new ArrayList<>(path));
    }

    private boolean findTarget(MazePoint point) {
        if (maze.isOutOfBounds(point))
            return false;

        if(!checked.add(point) || maze.isWall(point))
            return false;

        if(maze.isTarget(point) || goUp(point) || goDown(point) || goLeft(point) || goRight(point)) {
            path.add(point);
            return true;
        }

        return false;
    }

    private boolean goUp(MazePoint point) {
        return findTarget(point.goUp());
    }

    private boolean goDown(MazePoint point) {
        return findTarget(point.goDown());
    }

    private boolean goLeft(MazePoint point) {
        return findTarget(point.goLeft());
    }

    private boolean goRight(MazePoint point) {
        return findTarget(point.goRight());
    }
}
