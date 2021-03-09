package windows.Уxercises.Excercise1;

import Drawings.graphicElements.*;
import gui.DrawPanel;

import java.awt.*;

public class Sketch1 extends DrawPanel {

    Circle circle1;
    Circle circle2;
    Line line;

    @Override
    protected void setup() {
        showGrid = true;
        circle1 = makeCircle(-50, -20, 20);
        circle2 = makeCircle(50, 30, 10);
        line = makeLine(0, 0, 100, 100);
        line.setColor(Color.red);
    }

    @Override
    protected void draw() {
        circle1.setCenter(mouse.x, mouse.y);
        line.setPoints(mouse.x, mouse.y, 50, 30);
    }
}
