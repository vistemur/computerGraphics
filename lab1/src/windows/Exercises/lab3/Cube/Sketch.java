package windows.Exercises.lab3.Cube;

import Drawings.graphicElements.Dimension3d.Cube;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

public class Sketch extends DrawPanel {

    Cube cube;

    @Override
    protected void setup() {
        cube = makeCube();
        cube.setPoints(new Point3d(0, 0, 0), 10);
        cube.setFill(false);
    }

    @Override
    protected void draw() {
        cube.rotate((float) 0.004, (float) 0.005, (float) 0.006);
    }

    protected void mousePressed() {

    }
}
