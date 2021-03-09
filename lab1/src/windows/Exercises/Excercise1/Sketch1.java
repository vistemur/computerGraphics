package windows.Exercises.Excercise1;

import Drawings.graphicElements.*;
import gui.DrawPanel;

import java.awt.*;

public class Sketch1 extends DrawPanel {

    Circle circle1;
    Circle circle2;
    Line line;

    enum State {makingCircle1, sizingCircle1, makingCircle2, sizingCircle2, movingCircle1, movingCircle2, normal}
    State state;

    @Override
    protected void setup() {
        showGrid = true;
        circle1 = makeCircle(0, 0, 20);
        circle2 = makeCircle(50, 30, 10);
        line = makeLine(0, 0, 50, 30);
        state = State.makingCircle1;
        setColors();
        makeEverythingInvisible();
    }

    private void setColors() {
        circle1.setColor(Color.white);
        line.setColor(Color.red);
    }

    private void makeEverythingInvisible() {
        circle1.setVisible(false);
        circle2.setVisible(false);
        line.setVisible(false);
    }

    @Override
    protected void draw() {
        switch (state) {
            case sizingCircle1:
                circle1.setRadius(Math.abs(circle1.getCenter().x - mouse.x) + Math.abs(circle1.getCenter().y - mouse.y) / 2);
                break;
            case sizingCircle2:
                circle2.setRadius(Math.abs(circle2.getCenter().x - mouse.x) + Math.abs(circle2.getCenter().y - mouse.y) / 2);
                break;
        }
    }

    @Override
    protected void mouseClicked() {

    }

    @Override
    protected void mousePressed() {
        switch (state) {
            case makingCircle1:
                circle1.setCenter(mouse.getPoint());
                circle1.setRadius(0);
                circle1.setVisible(true);
                state = State.sizingCircle1;
                break;
            case makingCircle2:
                circle2.setCenter(mouse.getPoint());
                circle2.setRadius(0);
                circle2.setVisible(true);
                state = State.sizingCircle2;
                break;
        }
    }

    @Override
    protected void mouseReleased() {
        switch (state) {
            case sizingCircle1:
                state = State.makingCircle2;
                break;
            case sizingCircle2:
                countIntersectionPoints(circle1.getCenter(), circle1.getRadius(),
                                        circle2.getCenter(), circle2.getRadius());
                line.setVisible(true);
                state = State.normal;
                break;
        }
    }

    private void countIntersectionPoints(Point circle1Center, int circle1Radius, Point circle2Center, int circle2Radius) {
        Point point0 = new Point();
        Point point1 = new Point();

        // make super cool formula

        line.setPoint(0, point0);
        line.setPoint(1, point1);
    }
}
