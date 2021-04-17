package windows.Exercises.lab2.Excercise2_10;

import Drawings.CoordinateSpace.ConstantCoordinateSpace;
import Drawings.graphicElements.Circle;
import Drawings.graphicElements.Triangle;
import gui.DrawPanel;

import java.awt.*;

public class Sketch extends DrawPanel {

    Triangle triangle;
    Circle circle, circle2;

    @Override
    protected void setup() {
        setCoordinateSpace(new ConstantCoordinateSpace(-100, 100, 100, -100, 500, 500));
        setBackground(Color.white);
        triangle = makeTriangle(-10, -10, 10, 10, 10, -10);
        triangle.rotate(Math.PI / 4);
        circle = makeCircle(triangle.getCenter(), 1);
        circle2 = makeCircle(30, 30, 1);
        circle.setColor(Color.red);
    }

    @Override
    protected void draw() {

    }

    @Override
    protected void mousePressed() {

    }
}
