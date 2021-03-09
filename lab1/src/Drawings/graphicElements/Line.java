package Drawings.graphicElements;

import java.awt.*;

public class Line extends DrawElement {

    public Line(int x1, int y1, int x2, int y2) {
        setPoints(x1, y1, x2, y2);
    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        points = new int[][] {{x1, x2}, {y1, y2}};
        countDrawCoordinates();
    }

    protected void display(Graphics g) {
        g.drawLine(drawPoints[0][0], drawPoints[1][0], drawPoints[0][1], drawPoints[1][1]);
    }
}
