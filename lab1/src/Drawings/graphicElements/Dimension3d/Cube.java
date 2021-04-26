package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.Point3d;

public class Cube extends Triangled3dElement {
    public void setPoints(Point3d center, float sideLen) {
        float d = sideLen / 2;
        Triangle3d[] triangles = new Triangle3d[12];

        for (int c = 0; c < triangles.length; c++)
            triangles[c] = new Triangle3d();
        Point3d p1 = new Point3d(center.x - d , center.y - d, center.z - d);
        Point3d p2 = new Point3d(center.x - d , center.y - d, center.z + d);
        Point3d p3 = new Point3d(center.x - d , center.y + d, center.z - d);
        Point3d p4 = new Point3d(center.x - d , center.y + d, center.z + d);
        Point3d p5 = new Point3d(center.x + d , center.y - d, center.z - d);
        Point3d p6 = new Point3d(center.x + d , center.y - d, center.z + d);
        Point3d p7 = new Point3d(center.x + d , center.y + d, center.z - d);
        Point3d p8 = new Point3d(center.x + d , center.y + d, center.z + d);
        triangles[0].setPoints(p1, p2, p4);
        triangles[1].setPoints(p1, p3, p4);
        triangles[2].setPoints(p1, p2, p6);
        triangles[3].setPoints(p1, p5,p6);
        triangles[4].setPoints(p5, p6, p8);
        triangles[5].setPoints(p5, p7, p8);
        triangles[6].setPoints(p8, p4, p3);
        triangles[7].setPoints(p8, p7, p3);
        triangles[8].setPoints(p4, p2, p8);
        triangles[9].setPoints(p2, p8, p6);
        triangles[10].setPoints(p3, p1, p7);
        triangles[11].setPoints(p1, p7, p5);
        setTriangles(triangles);
    }
}
