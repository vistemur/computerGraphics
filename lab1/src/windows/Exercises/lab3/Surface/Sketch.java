package windows.Exercises.lab3.Surface;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Dimension2d.Circle;
import Drawings.graphicElements.Dimension3d.Surface;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

import java.awt.*;

public class Sketch extends DrawPanel {

    boolean rotateFast = false;
    Surface s;
    Circle c;

    @Override
    protected void setup() {
        setCoordinateSpace(new LinearCoordinateSpace(-1, 1, -1, 1));
        float size = 0.3f;
        s = makeSurface();
        s.setData(  new Point3d(-size, -size, -size), new Point3d(size, -size, size),
                    new Point3d(-size, size, 0), new Point3d(size, size, -size));
        s.move(0.5f, 0, 0);
        s.setFill(false);

        c = makeCircle(s.getCenter(), 1);
        c.setRadius(0.03f);
        c.setColor(Color.RED);
    }

    @Override
    protected void draw() {
        if (rotateFast) {
            s.rotate(new Point3d(0, 0, 0), 0.015f, 0.021f, 0.032f);
            s.rotate(0.15f, 0.14f, 0.13f);
        } else {
            s.rotate(new Point3d(0, 0, 0), 0.002f, 0.001f, 0.0025f);
            s.rotate(0.001f, 0.002f, 0.005f);
        }
        c.setCenter(s.getCenter());
    }

    @Override
    protected void mousePressed() {
        rotateFast = true;
    }

    @Override
    protected void mouseReleased() {
        rotateFast = false;
    }
}
