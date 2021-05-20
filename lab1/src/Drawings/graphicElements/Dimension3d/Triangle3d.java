package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.Point3d;

import java.awt.*;

public class Triangle3d extends DrawElement3d {

    private boolean normalIsRight = false;
    private Point3d normal;

    public Triangle3d() {
        setPoints(new Point3d(), new Point3d(), new Point3d());
    }

    public void setPoints(Point3d point1, Point3d point2, Point3d point3) {
        setPoints(new float[][] {{point1.x, point1.y, point1.z},
                                {point2.x, point2.y, point2.z},
                                {point3.x, point3.y, point3.z}});
        normalIsRight = false;
    }

    public Point3d getNormal() {
        if (normalIsRight == false)
            countNormal();
        return normal;
    }

    private void countNormal() {
        Point3d line1 = new Point3d(points[1][0] - points[0][0], points[1][1] - points[0][1], points[1][2] - points[0][2]);
        Point3d line2 = new Point3d(points[2][0] - points[0][0], points[2][1] - points[0][1], points[2][1] - points[0][2]);
        normal = new Point3d(
                line1.y * line2.z - line1.z * line2.y,
                line1.z * line2.x - line1.x * line2.z,
                line1.x * line2.y - line1.y * line2.x
        );
        float len = (float) Math.sqrt(normal.x * normal.x + normal.y * normal.y + normal.z * normal.z);
        normal.del(len);
        normalIsRight = true;
    }

    protected void display(Graphics g) {
        if (fill)
            g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
        else
            g.drawPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }

    protected void recountCoordinates() {
        normalIsRight = false;
    }
}
