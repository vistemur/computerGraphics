package Drawings.graphicElements.Dimension2d;

import java.awt.*;
import Drawings.graphicElements.Support.Point;

public class Triangle extends DrawElement {

    public Triangle() {
        points = new float[][] {{0, 0, 0}, {0, 0, 0}};
    }

    public Triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        points = new float[][] {{x1, x2, x3}, {y1, y2, y3}};
    }

    public void setPoints(Point point1, Point point2, Point point3) {
        setPoints(point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);
    }

    public void setPoints(float x1, float y1, float x2, float y2, float x3, float y3) {
        points[0][0] = x1;
        points[0][1] = x2;
        points[0][2] = x3;
        points[1][0] = y1;
        points[1][1] = y2;
        points[1][2] = y3;
        countDrawCoordinates();
    }

    public Point getPoint(int pointNumber) {
        pointNumber = Math.abs(pointNumber) % 3;
        return new Point(points[0][pointNumber], points[1][pointNumber]);
    }

    public void setPoint(int pointNumber, Point point) {
        pointNumber = Math.abs(pointNumber) % 3;
        points[0][pointNumber] = point.x;
        points[1][pointNumber] = point.y;
        countDrawCoordinates();
    }

    protected void display(Graphics g) {
        if (fill)
            g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
        else
            g.drawPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }
}
