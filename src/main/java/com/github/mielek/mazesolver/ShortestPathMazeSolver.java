package com.github.mielek.mazesolver;

import java.util.*;

public abstract class ShortestPathMazeSolver extends MazeSolver {

    List<MazePoint> path = null;

    public ShortestPathMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        if (path == null) {
            path = new ArrayList<>();
            if (maze.getStart().equals(maze.getTarget())) {
                path.add(maze.getStart());
            } else {
                path = createPathToTarget(createPathsTreeFromStart(maze.getStart()), maze.getTarget());
            }
        }
        return new MazePath(new ArrayList<>(path));
    }

    private List<MazePoint> createPathToTarget(Map<MazePoint, MazePoint> pathsTree, MazePoint target) {
        List<MazePoint> path = new LinkedList<>();
        MazePoint step = target;
        // check if a path exists
        if (pathsTree.get(step) != null) {
            path.add(step);
            while (pathsTree.get(step) != null) {
                step = pathsTree.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
        }
        return path;
    }

    protected abstract Map<MazePoint, MazePoint> createPathsTreeFromStart(MazePoint start);
}
