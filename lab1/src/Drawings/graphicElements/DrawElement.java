package Drawings.graphicElements;

import Drawings.CoordinateSpace.CoordinateSpace;

import java.awt.*;

public abstract class DrawElement implements Drawable {

    int[][] points = null;
    int[][] drawPoints;
    CoordinateSpace coordinateSpace = null;
    Color color = Color.black;
    boolean visible = true;

    @Override
    public void setCoordinateSpace(CoordinateSpace coordinateSpace) {
        this.coordinateSpace = coordinateSpace;
        countDrawCoordinates();
    }

    @Override
    public void countDrawCoordinates() {
        if (points != null && coordinateSpace != null)
            drawPoints = coordinateSpace.convert(points);
        recountCoordinates();
    }

    protected void setPoints(int[][] points) {
        this.points = points;
        if (coordinateSpace != null)
            countDrawCoordinates();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (visible)
            display(g);
    }

    protected void display(Graphics g) {}
    protected void recountCoordinates() {}
}
