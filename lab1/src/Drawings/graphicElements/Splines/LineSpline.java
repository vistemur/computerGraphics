package Drawings.graphicElements.Splines;

import Drawings.graphicElements.Splines.Spline;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class LineSpline extends Spline {

    public LineSpline(Point ... points) {
        setPoints(points);
    }

    public void setPoints(Point ... points) {
        buildingPoints = Arrays.copyOf(points, points.length);
        LinkedList<Point> actualPoints = new LinkedList<Point> (Arrays.asList(buildingPoints));
        setPoints(actualPoints);
        countDrawCoordinates();
    }
}
