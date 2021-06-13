package Drawings.graphicElements.Dimension2d;

import java.awt.*;

import Drawings.graphicElements.Support.Point;

public class Circle extends DrawElement {

    float radius;
    int drawRadiusX, drawRadiusY;

    public Circle(float x, float y, float radius) {
        setCenter(x, y);
        setRadius(radius);
    }

    public Circle(Point point, int radius) {
        this(point.x, point.y, radius);
    }

    public Point getCenter() {
        return new Point(points[0][0], points[1][0]);
    }

    public float getRadius() {
        return radius / 2;
    }

    public void setCenter(Point point) {
        setCenter(point.x, point.y);
    }

    public void setCenter(float x, float y) {
        setPoints(new float[][] {{x}, {y}});
    }

    public void setRadius(float radius) {
        this.radius = radius * 2;
        recountCoordinates();
    }

    @Override
    protected void display(Graphics g) {
        if (fill)
            g.fillOval(drawPoints[0][0] - drawRadiusX / 2, drawPoints[1][0] - drawRadiusY / 2, drawRadiusX, drawRadiusY);
        else
            g.drawOval(drawPoints[0][0] - drawRadiusX / 2, drawPoints[1][0] - drawRadiusY / 2, drawRadiusX, drawRadiusY);
    }

    @Override
    protected void recountCoordinates() {
        if (coordinateSpace != null) {
            drawRadiusX = coordinateSpace.convertLengthX(radius);
            drawRadiusY = coordinateSpace.convertLengthY(radius);
        }
    }
}
