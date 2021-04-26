package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.Point3d;

import java.awt.*;

public class Triangle3d extends DrawElement3d {

    public Triangle3d() {
        setPoints(new Point3d(), new Point3d(), new Point3d());
    }

    public void setPoints(Point3d point1, Point3d point2, Point3d point3) {
        setPoints(new float[][] {{point1.x, point1.y, point1.z},
                                {point2.x, point2.y, point2.z},
                                {point3.x, point3.y, point3.z}});
    }

    protected void display(Graphics g) {
        if (fill)
            g.fillPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
        else
            g.drawPolygon(new Polygon(drawPoints[0], drawPoints[1], 3));
    }
}
