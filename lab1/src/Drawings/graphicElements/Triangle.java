package Drawings.graphicElements;

import java.awt.*;

public class Triangle extends DrawElement {

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        points = new int[][] {{x1, x2, x3}, {y1, y2, y3}};
    }

    protected void display(Graphics g) {
        g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }
}
