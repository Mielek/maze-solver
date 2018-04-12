package com.github.mielek.mazesolver;

import java.util.*;

/**
 * Simplified BFS algorithm to find path in maze.
 */
public class SimplifiedBreadthFirstMazeSolver extends MazeSolver {

    List<MazePoint> path = null;

    public SimplifiedBreadthFirstMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public MazePath solve() {
        if(path==null){
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

    private Map<MazePoint, MazePoint> createPathsTreeFromStart(MazePoint start) {
        Set<MazePoint> checked = new HashSet<>();
        Queue<MazePoint> toBeChecked = new LinkedList<>();
        Map<MazePoint, MazePoint> previousPoint = new HashMap<>();
        toBeChecked.offer(start);

        while(!toBeChecked.isEmpty()){
            MazePoint current = toBeChecked.poll();
            checked.add(current);
            for(MazePoint next : Arrays.asList(current.goUp(), current.goDown(), current.goLeft(), current.goRight())) {
                if (!checked.contains(next) && isGoodPoint(next)) {
                    toBeChecked.offer(next);
                    if(!previousPoint.containsKey(next))
                        previousPoint.put(next, current);
                }
            }
        }
        return previousPoint;
    }

    private boolean isGoodPoint(MazePoint next) {
        return !(maze.isOutOfBounds(next) || maze.isWall(next));
    }

}
