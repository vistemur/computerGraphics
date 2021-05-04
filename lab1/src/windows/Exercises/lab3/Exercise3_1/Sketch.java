package windows.Exercises.lab3.Exercise3_1;

import Drawings.graphicElements.Dimension3d.Surface;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

public class Sketch extends DrawPanel {

    private SurfaceDataController data;
    private Surface s;
    public boolean pause = false;

    @Override
    protected void setup() {
        float size = 50f;
        s = makeSurface();
        s.setData(  new Point3d(-size, -size, -size), new Point3d(size, -size, size),
                new Point3d(-size, size, 0), new Point3d(size, size, -size));
        s.move(0, 0, -size);
        s.setFill(false);
    }

    public void setDataSource(SurfaceDataController data) {
        this.data = data;
    }

    public void setSurfacePoints(Point3d[] points) {
        try {
            s.setData(points[0], points[1], points[2], points[3]);
        } catch (Exception e) {
            System.out.println("ERROR: unable to set points from data source");
        }
    }

    @Override
    protected void draw() {
        if (!pause) {
            s.rotate(0.015f, 0.012f, 0.01f);
            data.setPointsFromSketch(getBuildingPoints());
        }
    }

    public Point3d[] getBuildingPoints() {
        return s.getBuildingPoints();
    }
}
