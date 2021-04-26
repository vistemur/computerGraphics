package Drawings.graphicElements.Splines;

import Drawings.graphicElements.Dimension2d.DrawElement;
import Drawings.graphicElements.Support.Point;


import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Spline extends DrawElement {

    public double precision = 0.01;
    protected Point[] buildingPoints;

    public Spline() {
        this.points = new float[2][0];
        countDrawCoordinates();
    }

    public void setPoints(Point ... points) {
        LinkedList <Point> actualPoints = new LinkedList <Point>();
        buildingPoints = Arrays.copyOf(points, points.length);

        fillActualPoints(actualPoints, points);
        setPoints(actualPoints);
        countDrawCoordinates();
    }

    protected void fillActualPoints(LinkedList <Point> actualPoints, Point[] points) {
        for (int pointNumber = 0; pointNumber < points.length - 1; pointNumber++) {
            for (double c = 0; c < 1; c += precision) {
                Point countPoint = countPoint(c, pointNumber);
                if (countPoint != null) {
                    actualPoints.push(countPoint(c, pointNumber));
                }
            }
        }
    }

    protected void setPoints(LinkedList <Point> actualPoints) {
        int counter = 0;
        int length = actualPoints.size();
        if (this.points == null || this.points[0].length != length)
            this.points = new float[2][length];
        for (Point point : actualPoints) {
            this.points[0][counter] = point.x;
            this.points[1][counter] = point.y;
            counter += 1;
        }
    }

    protected Point countPoint(double t, int pointNumber) {
        if (pointNumber == 0 || pointNumber >= buildingPoints.length - 2)
            return null;

        Point point;
        Point[] points = getSectorBuildingPoints(pointNumber);
        double loveToP0 = -t*t*t + 2*t*t - t;
        double loveToP1 = 3*t*t*t - 5*t*t + 2;
        double loveToP2 = -3*t*t*t + 4*t*t + t;
        double loveToP3 = t*t*t - t*t;

        point = new Point();
        point.x = (int) ((points[0].x * loveToP0 + points[1].x * loveToP1 + points[2].x * loveToP2 + points[3].x * loveToP3) / 2);
        point.y = (int) ((points[0].y * loveToP0 + points[1].y * loveToP1 + points[2].y * loveToP2 + points[3].y * loveToP3) / 2);
        return point;
    }

    private Point[] getSectorBuildingPoints(int pointNumber) {
        Point[] points = new Point[4];

        points[1] = buildingPoints[pointNumber];
        if (pointNumber > 0) {
            points[0] = buildingPoints[pointNumber - 1];
        } else {
            points[0] = new Point();
        }
        if (pointNumber < buildingPoints.length - 1) {
            points[2] = buildingPoints[pointNumber + 1];
            if (pointNumber < buildingPoints.length - 2) {
                points[3] = buildingPoints[ pointNumber + 2];
            } else {
                points[3] = new Point();
            }
        } else {
            points[2] = new Point();
            points[3] = new Point();
        }
        return points;
    }

    protected void sortBuildingPointsByX() {
        Point replacer;
        int back;

        for (int c = 0; c < buildingPoints.length - 1; c++) {
            if (buildingPoints[c].x > buildingPoints[c + 1].x) {
                for (back = c + 1; back > 0 && buildingPoints[back].x < buildingPoints[back - 1].x; back--) {
                    replacer = buildingPoints[back];
                    buildingPoints[back] = buildingPoints[back - 1];
                    buildingPoints[back - 1] = replacer;
                }
            }
        }
    }

    protected void display(Graphics g) {
        for (int c = 0; c < drawPoints[0].length - 1; c++) {
            g.drawLine(drawPoints[0][c], drawPoints[1][c], drawPoints[0][c + 1], drawPoints[1][c + 1]);
        }
    }
}
