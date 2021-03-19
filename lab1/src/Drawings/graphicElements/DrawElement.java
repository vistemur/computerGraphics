package Drawings.graphicElements;

import Drawings.CoordinateSpace.CoordinateSpace;

import java.awt.*;

public abstract class DrawElement implements Drawable {

    int[][] points = null;
    int[][] drawPoints;
    CoordinateSpace coordinateSpace = null;
    Color color = Color.black;
    boolean visible = true;
    boolean fill = true;

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

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (visible)
            display(g);
    }

    public int[][] getMatrix() {
        int[][] matrix;

        if (points.length > 0)
            matrix = new int[points[0].length][2];
        else
            return new int[0][0];
        for (int point = 0; point < matrix.length; point++) {
            matrix[point][0] = points[0][point];
            matrix[point][1] = points[1][point];
        }
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        int[][] points;

        points = new int[2][matrix.length];
        for (int point = 0; point < matrix.length; point++) {
            points[0][point] = matrix[point][0];
            points[1][point] = matrix[point][1];
        }
        this.points = points;
        countDrawCoordinates();
    }

    protected void display(Graphics g) {}
    protected void recountCoordinates() {}
}
