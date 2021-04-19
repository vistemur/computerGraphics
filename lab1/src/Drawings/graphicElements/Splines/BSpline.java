package Drawings.graphicElements.Splines;

import java.awt.*;
import java.util.LinkedList;

public class BSpline extends Spline {

    private int k = 2;

    public void setK(int k) {
       if (k > 0 && k < buildingPoints.length)
           this.k = k;
    }

    protected void fillActualPoints(LinkedList<Point> actualPoints, Point[] points) {
        for (double t = t(k - 1) + precision; t < t(buildingPoints.length); t += precision) {
            actualPoints.push(countPoint(t));
        }
    }

    private Point countPoint(double t) {
        Point point = new Point();

        for (int i = 0; i < buildingPoints.length; i++) {
                double love = countLove(i, k, t);
                point.x += buildingPoints[i].x * love;
                point.y += buildingPoints[i].y * love;
        }
        return point;
    }

    private double countLove(int i, int k, double t) {
        if (k == 1) {
            if (t(i) <= t && t <= t(i + 1))
                return 1;
            else
                return 0;
        }
        return ((t - t(i)) / (t(i + k - 1) - t(i))) * countLove(i, k - 1, t) + ((t(i + k) - t) / (t(i + k) - t(i + 1))) * countLove(i + 1, k - 1, t);
    }

    private double t(int i) {
        return ((double) i) / buildingPoints.length;
    }

    private Point[] getPoints(int i) {
        Point[] points = new Point[2];
        try {
            points[0] = buildingPoints[i];
        } catch (Exception e) {
            points[0] = new Point();
        }
        try {
            points[1] = buildingPoints[i + 1];
        } catch (Exception e) {
            points[1] = new Point();
        }
        return points;
    }
}
