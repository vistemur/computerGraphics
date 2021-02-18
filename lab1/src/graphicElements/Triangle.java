package graphicElements;

import java.awt.*;

public class Triangle implements Drawable{

    int[][] points;
    int[][] drawPoints;
    Color color;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        points = new int[][] {{x1, x2, x3}, {y1, y2, y3}};
        drawPoints = new int[][] {{x1, x2, x3}, {y1, y2, y3}};
        color = Color.black;
    }

    public void setCoordinates(int min, int max, int newMin, int newMax) {
        
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }
}
