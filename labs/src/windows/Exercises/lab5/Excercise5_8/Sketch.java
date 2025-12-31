package windows.Exercises.lab5.Excercise5_8;

import Drawings.graphicElements.Dimension3d.Cube;
import Drawings.graphicElements.Dimension3d.Visibility.Normaler;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

public class Sketch extends DrawPanel {

    Cube cube;

    @Override
    protected void setup() {
        cube = makeCube();
        cube.setPoints(new Point3d(0, 0, 0), 40);
        cube.setFill(false);
        cube.setVisibilityController(new Normaler());
    }

    @Override
    protected void draw() {
        cube.rotate((float) 0.004, (float) 0.005, (float) 0.006);
    }
}
