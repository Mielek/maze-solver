package com.github.mielek.mazesolver;

import java.util.ArrayList;
import java.util.List;

public class MazePath {
    private List<MazePoint> points = new ArrayList<>();

    public MazePath() {
    }

    public List<MazePoint> getPoints() {
        return points;
    }
}
