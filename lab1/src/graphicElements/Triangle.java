package graphicElements;

import java.awt.*;

public class Triangle implements Drawable{

    int[] x;
    int[] y;
    Color color;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        x = new int[] {x1, x2, x3};
        y = new int[] {y1, y2, y3};
        color = Color.black;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillPolygon(new Polygon(x, y, 3));
    }
}
