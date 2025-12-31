package windows.Exercises.lab6.Excercise6_4;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Dimension3d.Cube;
import Drawings.graphicElements.Dimension3d.Sphere;
import Drawings.graphicElements.Dimension3d.Triangle3d;
import Drawings.graphicElements.Dimension3d.Triangled3dElement;
import Drawings.graphicElements.Dimension3d.Visibility.ZSorter;
import Drawings.graphicElements.Support.Point3d;
import gui.DrawPanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Sketch extends DrawPanel {

    Triangled3dElement element;
    float rotationRight = 0, rotationUp = 0;

    @Override
    protected void setup() {
        setCoordinateSpace(new LinearCoordinateSpace(-1, 1, -1, 1));
        setModel("sphere");
    }

    @Override
    protected void draw() {
        if (rotationRight != 0 || rotationUp != 0) {
            element.rotate(rotationUp, rotationRight, 0);
            lightFromZ();
        }
    }

    public void setModel(String model) {
        if (element != null)
            removeElement(element);
        switch (model.toLowerCase()) {
            case "cube" -> setElementCube();
            case "sphere" -> setElementSphere();
        }
    }

    private void setElementCube() {
        element = makeCube();
        ((Cube) element).setPoints(new Point3d(), 0.3f);
        element.setVisibilityController(new ZSorter());
        lightFromZ();
    }

    private void setElementSphere() {
        element = makeSphere();
        ((Sphere) element).set(new Point3d(), 0.3f);
        ((Sphere) element).setSteps(20);
        element.setVisibilityController(new ZSorter());
        lightFromZ();
    }

    private void lightFromZ() {
        for (Triangle3d triangle : element.getTriangles()) {
            float znach = triangle.getCenter().z * -1;
            triangle.setColor(new Color((znach + 1) / 2, (znach + 1) / 2, (znach + 1) / 2));
        }
    }

    @Override
    protected void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39 -> rotationRight = 0.01f;
            case 37 -> rotationRight = -0.01f;
            case 38 -> rotationUp = 0.01f;
            case 40 -> rotationUp = -0.01f;
        }
    }

    @Override
    protected void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39, 37 -> rotationRight = 0;
            case 38, 40 -> rotationUp = 0;
        }
    }
}
