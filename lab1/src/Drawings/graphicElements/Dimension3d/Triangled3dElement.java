package Drawings.graphicElements.Dimension3d;

import Drawings.CoordinateSpace.CoordinateSpace;
import Drawings.graphicElements.Dimension3d.Visibility.VisibilityController;
import Drawings.graphicElements.Drawable;
import Drawings.graphicElements.Support.BuildingPoints;
import Drawings.graphicElements.Support.Point3d;

import java.awt.*;

public class Triangled3dElement implements Drawable {

    protected Triangle3d[] triangles;
    protected static CoordinateSpace coordinateSpace = null;
    protected Point3d center = new Point3d();
    protected boolean centerIsSet = false;
    protected BuildingPoints buildingPoints = null;
    private boolean visible = true;
    protected boolean fill = true;
    protected VisibilityController visibilityController;

    public Triangled3dElement() {
        this.triangles = new Triangle3d[0];
    }

    protected void setTriangles(Triangle3d[] triangles) {
        this.triangles = triangles;
        setCoordinateSpace(coordinateSpace);
        countDrawCoordinates();
        centerIsSet = false;
        setFill(fill);
        setVisible(visible);
    }

    public void setFill(boolean fill) {
        this.fill = fill;
        for (Triangle3d triangle : triangles)
            triangle.setFill(fill);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        for (Triangle3d triangle : triangles)
            triangle.setVisible(visible);
    }

    public void setColor(Color color) {
        for (Triangle3d triangle : triangles)
            triangle.setColor(color);
    }

    public void setVisibilityController(VisibilityController visibilityController) {
        this.visibilityController = visibilityController;
        countTrianglesVisibility();
    }

    private void countTrianglesVisibility() {
        if (visibilityController != null)
            visibilityController.SetVisible(triangles);
    }

    public Point3d[] getBuildingPoints() {
        return buildingPoints.getPoints();
    }

    public void move(float x, float y, float z) {
        for (Triangle3d triangle : triangles)
            triangle.move(x, y, z);
        if (buildingPoints != null)
            buildingPoints.move(x, y, z);
        centerIsSet = false;
        countTrianglesVisibility();
    }

    public void rotate(float ... rotation) { // x, y, z
        rotate(getCenter(), rotation);
    }

    public void rotate(Point3d point, float ... rotation) { // x, y, z
        for (Triangle3d triangle : triangles)
            triangle.rotate(point, rotation);
        if (buildingPoints != null)
            buildingPoints.rotate(point, rotation);
        centerIsSet = false;
        countTrianglesVisibility();
    }

    public Point3d getCenter() {
        if (centerIsSet == false)
            countCenter();
        return center;
    }

    protected void countCenter() {
        center.x = 0;
        center.y = 0;
        center.z = 0;
        for (Triangle3d triangle : triangles)
            center.add(triangle.getCenter());
        center.del(triangles.length);
        centerIsSet = true;
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
        countTrianglesVisibility();
    }
}
