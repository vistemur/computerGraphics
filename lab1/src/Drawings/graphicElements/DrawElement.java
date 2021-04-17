package Drawings.graphicElements;

import Drawings.CoordinateSpace.CoordinateSpace;
import Matrix.Matrix;

import java.awt.*;

public class DrawElement implements Drawable {

    int[][] points = null;
    int[][] drawPoints;
    static CoordinateSpace coordinateSpace = null;
    Color color = Color.black;
    boolean visible = true;
    boolean fill = true;

    @Override
    public void setCoordinateSpace(CoordinateSpace coordinateSpace) {
        DrawElement.coordinateSpace = coordinateSpace;
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

    public void move(int x, int y) {
        int[][] matrix = getMatrix();
        setMatrix(Matrix.multiply(Matrix.resize(matrix, 3, matrix.length), new int[][] {{1, 0, 0}, {0, 1, 0}, {x, y, 1}}));
    }

    // precision is too bad
    public void rotate(double radians) {
        rotate(getCenter(), radians);
    }

    public void rotate(Point point, double radians) {
        move(-point.x, -point.y);
        setMatrix(Matrix.multiply(getMatrix(), new double[][] {{Math.cos(radians), Math.sin(radians)}, {-Math.sin(radians), Math.cos(radians)}}));
        move(point.x, point.y);
    }

    public void resize(double multiplier) {
        Point center = getCenter();
        move(-center.x, -center.y);
        setMatrix(Matrix.multiply(getMatrix(), new double[][] {{multiplier, 0}, {0, multiplier}}));
        move(center.x, center.y);
    }

    public Point getCenter() {
        Point center;

        center = new Point(0, 0);
        if (points != null) {
            if (points.length == 2 && points[0].length > 0) {
                for (int point = 0; point < points[0].length; point++) {
                    center.x += points[0][point];
                    center.y += points[1][point];
                }
                center.x /= points[0].length;
                center.y /= points[0].length;
            }
        }
        return center;
    }

    protected void display(Graphics g) {}
    protected void recountCoordinates() {}
}
