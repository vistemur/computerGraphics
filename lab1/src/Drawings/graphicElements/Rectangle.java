package Drawings.graphicElements;

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
}
