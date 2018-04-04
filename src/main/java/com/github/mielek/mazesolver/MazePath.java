package com.github.mielek.mazesolver;

import java.util.ArrayList;
import java.util.List;

public class MazePath {
    private List<MazePoint> points = new ArrayList<>();

    public MazePath() {
    }

    public MazePath(List<MazePoint> points) {
        this.points = points;
    }

    public List<MazePoint> getPoints() {
        return points;
    }

    void setPoints(List<MazePoint> points) {
        this.points = points;
    }
}
