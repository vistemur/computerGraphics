package Drawings.graphicElements;

import java.awt.*;

public class Circle extends DrawElement {

    int radius;
    int drawRadiusX, drawRadiusY;

    public Circle(int x, int y, int radius) {
        setCenter(x, y);
        setRadius(radius);
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
