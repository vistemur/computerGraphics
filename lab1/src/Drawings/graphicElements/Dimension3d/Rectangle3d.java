package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.Point3d;

public class Rectangle3d extends Triangled3dElement {

    public void setPoints(Point3d refPoint1, Point3d refPoint2) {
        Triangle3d[] triangles = new Triangle3d[2];
        Point3d point1, point2, point3;

        triangles[0] = new Triangle3d();
        triangles[1] = new Triangle3d();
        point1 = new Point3d(refPoint1);
        point2 = new Point3d((refPoint1.x + refPoint2.x) / 2, refPoint1.y, (refPoint1.z + refPoint2.z) / 2);
        point3 = new Point3d((refPoint1.x + refPoint2.x) / 2, refPoint2.y, (refPoint1.z + refPoint2.z) / 2);
        triangles[0].setPoints(point1, point2, point3);
        point1 = new Point3d(refPoint2);
        triangles[1].setPoints(point1, point2, point3);
        setTriangles(triangles);
    }

    public void setPoints(Point3d refPoint1, Point3d refPoint2, Point3d refPoint3, Point3d refPoint4) {
        Triangle3d[] triangles = new Triangle3d[2];
        Point3d point1, point2, point3;

        triangles[0] = new Triangle3d();
        triangles[1] = new Triangle3d();
        point1 = new Point3d(refPoint1);
        point2 = new Point3d(refPoint2);
        point3 = new Point3d(refPoint3);
        triangles[0].setPoints(point1, point2, point3);
        point1 = new Point3d(refPoint4);
        triangles[1].setPoints(point1, point2, point3);
        setTriangles(triangles);
    }
}
