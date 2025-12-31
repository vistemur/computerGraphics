package Drawings.graphicElements.Dimension2d;

import Drawings.graphicElements.Support.Point;

import java.awt.*;

public class Rectangle extends DrawElement {

    public Rectangle(int x, int y, int width, int height) {
        points = new float[][] {{x, x + width, x + width, x}, {y, y, y - height, y - height}};
    }

    protected void display(Graphics g) {
        Polygon polygon = getPolygon();
        if (fill)
            g.fillPolygon(polygon);
        else
            g.drawPolygon(polygon);
    }

    protected Polygon getPolygon() {
        return new Polygon(drawPoints[0], drawPoints[1], 4);
    }

    public void setCenter(Point point) {
        Point newCenter = new Point(point);
        Point center = getCenter();
        center.mul(-1);
        newCenter.add(center);
        move(newCenter.x, newCenter.y);
    }

    public Point getCenter() {
        return countCenter(new Point[] {new Point(points[0][0], points[1][0]),
                                        new Point(points[0][1], points[1][1]),
                                        new Point(points[0][2], points[1][2]),
                                        new Point(points[0][3], points[1][3])});
    }
}
