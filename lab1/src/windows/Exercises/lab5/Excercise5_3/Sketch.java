package windows.Exercises.lab5.Excercise5_3;

import Drawings.graphicElements.Dimension3d.Cube;
import Drawings.graphicElements.Dimension3d.Visibility.ZSorter;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

import java.awt.*;

public class Sketch extends DrawPanel {

    Cube cube;

    @Override
    protected void setup() {
        cube = makeCube();
        cube.setPoints(new Point3d(0, 0, 0), 40);
        cube.setColor(Color.red, Color.blue, Color.green, Color.black, Color.yellow, Color.gray);
        cube.setVisibilityController(new ZSorter());
    }

    @Override
    protected void draw() {
        cube.rotate((float) 0.004, (float) 0.005, (float) 0.006);
    }
}
