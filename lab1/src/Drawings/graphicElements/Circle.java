package Drawings.graphicElements;

import java.awt.*;

public class Circle extends DrawElement {

    int radius;
    int drawRadiusX, drawRadiusY;

    public Circle(int x, int y, int radius) {
        setCenter(x, y);
        setRadius(radius);
    }

    public Circle(Point point, int radius) {
        this(point.x, point.y, radius);
    }

    public Point getCenter() {
        return new Point(points[0][0], points[1][0]);
    }

    public int getRadius() {
        return radius / 2;
    }

    public void setCenter(Point point) {
        setCenter(point.x, point.y);
    }

    public void setCenter(int x, int y) {
        setPoints(new int[][] {{x}, {y}});
    }

    public void setRadius(int radius) {
        this.radius = radius * 2;
    }

    @Override
    protected void display(Graphics g) {
        g.fillOval(drawPoints[0][0] - drawRadiusX / 2, drawPoints[1][0] - drawRadiusY / 2, drawRadiusX, drawRadiusY);
    }

    @Override
    protected void recountCoordinates() {
        drawRadiusX = coordinateSpace.convertX(radius);
        drawRadiusY = coordinateSpace.convertY(radius);
    }
}
