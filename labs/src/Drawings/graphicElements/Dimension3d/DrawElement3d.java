package Drawings.graphicElements.Dimension3d;

import Drawings.CoordinateSpace.CoordinateSpace;
import Drawings.graphicElements.Dimension3d.Visibility.VisibilityController;
import Drawings.graphicElements.Drawable;
import Drawings.graphicElements.Support.*;
import Matrix.Matrix;

import java.awt.*;

public class DrawElement3d  implements Drawable {

    protected float[][] points = null; // {{x0, y0, z0}, {x1, y1, z1}}
    protected int[][] drawPoints;
    protected static CoordinateSpace coordinateSpace = null;
    private Color color = Color.black;
    private boolean visible = true;
    protected boolean fill = true;

    private float[][] getPointsForDrawing(float[][] initialPoints) { // {{x0, x1}, {y0, y1}}
        float[][] pointsForDrawing; // {{x0, y0}, {x1, y1}}
        float[][] answer;

        // get matrix 2 n (project 3d on 2d screen)
        pointsForDrawing = getProjection(initialPoints); // {{x0, y0}, {x1, y1}}

        // prepare for drawing to {{x0, x1}, {y0, y1}} look
        answer = new float[2][initialPoints.length];
        for (int c = 0; c < initialPoints.length; c++) {
            answer[0][c] = pointsForDrawing[c][0];
            answer[1][c] = pointsForDrawing[c][1];
        }
        return answer;
    }

    private float[][] getProjection(float[][] initialPoints) {
        return Matrix.resize(initialPoints, 2, initialPoints.length);
    }

    @Override
    public void setCoordinateSpace(CoordinateSpace coordinateSpace) {
        DrawElement3d.coordinateSpace = coordinateSpace;
        countDrawCoordinates();
    }

    @Override
    public void countDrawCoordinates() {
        if (points != null && coordinateSpace != null)
            drawPoints = coordinateSpace.convert(getPointsForDrawing(points));
        recountCoordinates();
    }

    protected void setPoints(float[][] points) {
        this.points = points;
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

    public Point3d getCenter() {
        Point3d center;

        center = new Point3d();
        if (points != null) {
            if (points.length > 0 && points[0].length == 3) {
                for (int point = 0; point < points.length; point++)
                    center.add(points[point][0], points[point][1], points[point][2]);
                center.del(points.length);
            }
        }
        return center;
    }

    public void move(float x, float y, float z) {
        if (points != null) {
            if (points.length > 0 && points[0].length == 3) {
                for (int point = 0; point < points.length; point++) {
                    points[point][0] += x;
                    points[point][1] += y;
                    points[point][2] += z;
                }
            }
        }
        countDrawCoordinates();
    }

    public void rotate(float ... rotation) { // x, y, z
        Point3d center = getCenter();

        move(-center.x, -center.y, -center.z);
        if (rotation.length > 0)
            rotateX(rotation[0]);
        if (rotation.length > 1)
            rotateY(rotation[1]);
        if (rotation.length > 2)
            rotateZ(rotation[2]);
        move(center.x, center.y, center.z);
    }

    public void rotate(Point3d point, float ... rotation) { // x, y, z
        move(-point.x, -point.y, -point.z);
        if (rotation.length > 0)
            rotateX(rotation[0]);
        if (rotation.length > 1)
            rotateY(rotation[1]);
        if (rotation.length > 2)
            rotateZ(rotation[2]);
        move(point.x, point.y, point.z);
    }

    private void rotateX(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {1, 0                      , 0},
                        {0, (float) Math.cos(angle), (float) -Math.sin(angle)},
                        {0, (float) Math.sin(angle), (float) Math.cos(angle)}}));
    }

    private void rotateY(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {(float) Math.cos(angle) , 0, (float) Math.sin(angle)},
                        {0                       , 1, 0},
                        {(float) -Math.sin(angle), 0, (float) Math.cos(angle)}}));
    }

    private void rotateZ(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {(float) Math.cos(angle), (float) -Math.sin(angle), 0},
                        {(float) Math.sin(angle), (float) Math.cos(angle) , 0},
                        {0,                       0                       , 1}}));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (visible)
            display(g);
    }

    public float[][] getMatrix() {
        return points;
    }

    public void setMatrix(float[][] matrix) {
        setPoints(matrix);
        countDrawCoordinates();
    }

    protected void display(Graphics g) {}
    protected void recountCoordinates() {}
}