package windows.exercises.Excercise1;

import graphicElements.Triangle;
import gui.DrawPanel;

public class Sketch1 extends DrawPanel {

    Triangle triangle;

    @Override
    protected void setup() {
        triangle = makeTriangle(0, 0, 0, 100, 100, 100);
    }

    @Override
    protected void draw() {

    }
}
