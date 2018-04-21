package com.github.mielek.mazesolver;

import java.util.*;

/**
 * Simplified BFS algorithm to find path in maze.
 */
public class SimplifiedBreadthFirstMazeSolver extends ShortestPathMazeSolver {

    List<MazePoint> path = null;

    public SimplifiedBreadthFirstMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    protected Map<MazePoint, MazePoint> createPathsTreeFromStart(MazePoint start) {
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
