package windows.Exercises.lab3.Exercise3_1;

import Drawings.graphicElements.Dimension2d.Circle;
import Drawings.graphicElements.Dimension3d.Surface;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

import java.awt.*;

public class Sketch extends DrawPanel {

    private Exercise3_1.SketchPointsDataSetter data;
    private Surface s;
    public boolean pause = false;
    private Circle[] pointsCircles;
    private float rotationX, rotationY, rotationZ;

    @Override
    protected void setup() {
        float size = 50f;
        rotationX = 0.015f;
        rotationY = 0.012f;
        rotationZ = 0.01f;

        Point3d point1 = new Point3d(-size, -size, -size);
        Point3d point2 = new Point3d(size, -size, size);
        Point3d point3 = new Point3d(-size, size, 0);
        Point3d point4 = new Point3d(size, size, -size);

        s = makeSurface();
        s.setData(  point1, point2, point3, point4);
        s.move(0, 0, -size);
        s.setFill(false);

        pointsCircles = new Circle[4];
        for (int circle = 0; circle < pointsCircles.length; circle++) {
            pointsCircles[circle] = makeCircle(0, 0, 3);
        }
        pointsCircles[0].setColor(Color.red);
        pointsCircles[0].setCenter(point1);
        pointsCircles[1].setColor(Color.green);
        pointsCircles[1].setCenter(point2);
        pointsCircles[2].setColor(Color.blue);
        pointsCircles[2].setCenter(point3);
        pointsCircles[3].setColor(Color.black);
        pointsCircles[3].setCenter(point4);
    }

    public void setDataSource(Exercise3_1.SketchPointsDataSetter data) {
        this.data = data;
    }

    public void setRotation(float rotationX, float rotationY, float rotationZ) {
        this.rotationX = rotationX;
        this.rotationY = rotationY;
        this.rotationZ = rotationZ;
    }

    public void setSurfacePoints(Point3d[] points) {
        try {
            s.setData(points[0], points[1], points[2], points[3]);
            for (int i = 0; i < 4; i++)
                pointsCircles[i].setCenter(points[i]);
        } catch (Exception e) {
            System.out.println("ERROR: unable to set points from data source");
        }
    }

    @Override
    protected void draw() {
        if (!pause) {
            s.rotate(rotationX, rotationY, rotationZ);
            Point3d[] points = getBuildingPoints();
            data.setPointsData(getBuildingPoints());
            for (int i = 0; i < 4; i++)
                pointsCircles[i].setCenter(points[i]);
        }
    }

    public Point3d[] getBuildingPoints() {
        return s.getBuildingPoints();
    }
}
