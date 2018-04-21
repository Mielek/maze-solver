package com.github.mielek.mazesolver;

import java.util.*;

public class DijkstraMazeSolver extends ShortestPathMazeSolver {

    List<MazePoint> path = null;

    public DijkstraMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    protected Map<MazePoint, MazePoint> createPathsTreeFromStart(MazePoint start) {
        Set<MazePoint> checked = new HashSet<>();
        LinkedList<MazePoint> toBeChecked = new LinkedList<>();
        Map<MazePoint, MazePoint> previousPoint = new HashMap<>();
        Map<MazePoint, Double> travelCost = new HashMap<>();
        travelCost.put(start, .0);
        toBeChecked.offer(start);

        while (!toBeChecked.isEmpty()) {
            Collections.sort(toBeChecked, (x, y) -> (int) (travelCost.get(x) - travelCost.get(y)));
            MazePoint current = toBeChecked.poll();
            checked.add(current);
            for (MazePoint next : Arrays.asList(current.goUp(), current.goDown(), current.goLeft(), current.goRight())) {
                if (!checked.contains(next) && isGoodPoint(next)) {
                    toBeChecked.offer(next);
                    double currentTravelCostToNext = travelCost.get(current) + 1;
                    if (!previousPoint.containsKey(next)) {
                        travelCost.put(next, currentTravelCostToNext);
                        previousPoint.put(next, current);
                    } else {
                        double previousTravelCostToNext = travelCost.get(next);
                        if (previousTravelCostToNext > currentTravelCostToNext) {
                            travelCost.put(next, currentTravelCostToNext);
                            previousPoint.put(next, current);
                        }
                    }
                }
            }
        }
        return previousPoint;
    }

    private boolean isGoodPoint(MazePoint next) {
        return !(maze.isOutOfBounds(next) || maze.isWall(next));
    }
}
