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
        circle1 = makeCircle(0, 0, 20);
        circle2 = makeCircle(50, 30, 10);
        line = makeLine(0, 0, 50, 30);
        setColors();
    }

    private void setColors() {
        circle1.setColor(Color.white);
        line.setColor(Color.red);
    }

    @Override
    protected void draw() {
        circle1.setCenter(mouse.getPoint());
        line.setPoint(0, countIntersectionPoint(circle1.getCenter(), circle1.getRadius(), line.getPoint(1)));
    }

    private Point countIntersectionPoint(Point circleCenter, int radius, Point anotherLinePoint) {
        Point point = new Point();

        point.x = mouse.x; // make super cool formula
        point.y = mouse.y;

        return point;
    }
}
