package Drawings.graphicElements;

import java.awt.*;

public class Rectangle extends DrawElement {

    public Rectangle(int x, int y, int width, int height) {
        points = new int[][] {{x, x + width, x + width, x}, {y, y, y + height, y + height}};
    }

    protected void display(Graphics g) {
        Polygon polygon;

        polygon = new Polygon(drawPoints[0], drawPoints[1], 4);
        if (fill)
            g.fillPolygon(polygon);
        else
            g.drawPolygon(polygon);
    }
}
