package Drawings.graphicElements;

import Drawings.CoordinateSpace.CoordinateSpace;
import Matrix.Matrix;
import Drawings.graphicElements.Support.Point;

import java.awt.*;

public class DrawElement implements Drawable {

    protected float[][] points = null;
    protected int[][] drawPoints;
    protected static CoordinateSpace coordinateSpace = null;
    private Color color = Color.black;
    private boolean visible = true;
    protected boolean fill = true;

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

    protected void setPoints(float[][] points) {
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

    public float[][] getMatrix() {
        float[][] matrix;

        if (points.length > 0)
            matrix = new float[points[0].length][2];
        else
            return new float[0][0];
        for (int point = 0; point < matrix.length; point++) {
            matrix[point][0] = points[0][point];
            matrix[point][1] = points[1][point];
        }
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        float[][] points;

        points = new float[2][matrix.length];
        for (int point = 0; point < matrix.length; point++) {
            points[0][point] = matrix[point][0];
            points[1][point] = matrix[point][1];
        }
        this.points = points;
        countDrawCoordinates();
    }

    public void move(float x, float y) {
        float[][] matrix = getMatrix();
        setMatrix(Matrix.multiply(Matrix.resize(matrix, 3, matrix.length), new float[][] {{1F, 0F, 0F},
                                                                                                {0F, 1F, 0F},
                                                                                                {x , y , 1F}}));
    }

    // precision is too bad
    public void rotate(float radians) {
        rotate(getCenter(), radians);
    }

    public void rotate(Point point, float radians) {
        move(-point.x, -point.y);
        setMatrix(Matrix.multiply(getMatrix(), new float[][] {  { (float) Math.cos(radians) , (float) Math.sin(radians)},
                                                                { (float) -Math.sin(radians), (float) Math.cos(radians)}}));
        move(point.x, point.y);
    }

    public void resize(float multiplier) {
        Point center = getCenter();
        move(-center.x, -center.y);
        setMatrix(Matrix.multiply(getMatrix(), new float[][] {{multiplier, 0F}, {0F, multiplier}}));
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
