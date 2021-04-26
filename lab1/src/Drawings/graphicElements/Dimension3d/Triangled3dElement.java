package Drawings.graphicElements.Dimension3d;

import Drawings.CoordinateSpace.CoordinateSpace;
import Drawings.graphicElements.Dimension3d.Triangle3d;
import Drawings.graphicElements.Drawable;
import Drawings.graphicElements.Support.Point3d;
import Matrix.Matrix;

import java.awt.*;

public class Triangled3dElement implements Drawable {

    Triangle3d[] triangles;
    protected static CoordinateSpace coordinateSpace = null;

    public Triangled3dElement() {
        this.triangles = new Triangle3d[0];
    }

    protected void setTriangles(Triangle3d[] triangles) {
        this.triangles = triangles;
        setCoordinateSpace(coordinateSpace);
        countDrawCoordinates();
    }

    public void setFill(boolean fill) {
        for (Triangle3d triangle : triangles)
            triangle.setFill(fill);
    }

    public void move(float x, float y, float z) {
        for (Triangle3d triangle : triangles)
            triangle.move(x, y, z);
    }

    public void rotate(float ... rotation) { // x, y, z
        rotate(getCenter(), rotation);
    }

    public void rotate(Point3d point, float ... rotation) { // x, y, z
        for (Triangle3d triangle : triangles)
            triangle.rotate(point, rotation);
    }

    public Point3d getCenter() {
        Point3d center;

        center = new Point3d();
        for (Triangle3d triangle : triangles)
            center.add(triangle.getCenter());
        center.del(triangles.length);
        return center;
    }

    @Override
    public void draw(Graphics g) {
        for (Triangle3d triangle : triangles)
            triangle.draw(g);
    }

    @Override
    public void setCoordinateSpace(CoordinateSpace coordinateSpace) {
        Triangled3dElement.coordinateSpace = coordinateSpace;
        for (Triangle3d triangle : triangles)
            triangle.setCoordinateSpace(coordinateSpace);
    }

    @Override
    public void countDrawCoordinates() {
        for (Triangle3d triangle : triangles)
            triangle.countDrawCoordinates();
    }
}
