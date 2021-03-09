package Drawings.graphicElements;

import java.awt.*;

public class Line extends DrawElement {

    public Line(int x1, int y1, int x2, int y2) {
        setPoints(x1, y1, x2, y2);
    }

    public Line(Point point1, Point point2) {
        this(point1.x, point1.y, point2.x, point2.y);
    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        points = new int[][] {{x1, x2}, {y1, y2}};
        countDrawCoordinates();
    }

    public Point getPoint(int pointNumber) {
        Point point = new Point();

        if (pointNumber > -1 && pointNumber < 2) {
            point.x = points[0][pointNumber];
            point.y = points[1][pointNumber];
        }
        return point;
    }

    public void setPoint(int pointNumber, Point point) {
        if (pointNumber > -1 && pointNumber < 2) {
            points[0][pointNumber] = point.x;
            points[1][pointNumber] = point.y;
            countDrawCoordinates();
        }
    }

    protected void display(Graphics g) {
        g.drawLine(drawPoints[0][0], drawPoints[1][0], drawPoints[0][1], drawPoints[1][1]);
    }
}
