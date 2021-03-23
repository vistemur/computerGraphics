package Drawings.graphicElements;

import java.awt.*;

public class Triangle extends DrawElement {

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        points = new int[][] {{x1, x2, x3}, {y1, y2, y3}};
    }

    public void setPoints(Point point1, Point point2, Point point3) {
        setPoints(point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);
    }

    public void setPoints(int x1, int y1, int x2, int y2, int x3, int y3) {
        points[0][0] = x1;
        points[0][1] = x2;
        points[0][2] = x3;
        points[1][0] = y1;
        points[1][1] = y2;
        points[1][2] = y3;
        countDrawCoordinates();
    }

    protected void display(Graphics g) {
        g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }
}
