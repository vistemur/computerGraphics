package windows.Exercises.lab2.Spline;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Splines.Spline;
import gui.DrawPanel;
import windows.Exercises.lab2.Helpers.MovableCircles;

import java.awt.*;

public class Sketch extends DrawPanel {

    int size;

    Spline spline;
    int pointsAmount;
    MovableCircles movableCircles;

    @Override
    protected void setup() {
        buildCoordinateSpace();
        makeMovableCircles(10);
        buildSpline();
    }

    private void buildCoordinateSpace() {
        size = 10000;
        setCoordinateSpace(new LinearCoordinateSpace(-size, size, -size, size));
        setBackground(Color.white);
    }

    private void buildSpline() {
        spline = makeSpline();
        spline.precision = 10.0 / size;
        spline.setPoints(movableCircles.getPoints());
    }

    private void makeMovableCircles(int pointsAmount) {
        movableCircles = new MovableCircles(pointsAmount);
        for (int c = 0; c < pointsAmount; c++)
            movableCircles.setCircle(c, makeCircle((size * 2) / pointsAmount * c - (int) (size * 0.9), 0, size / 50));
    }

    @Override
    protected void draw() {
        movableCircles.moveChosenCircle(mouse);
        if (movableCircles.circleIsMoving())
            spline.setPoints(movableCircles.getPoints());
    }

    @Override
    protected void mousePressed() {
        movableCircles.pick(mouse);
    }

    @Override
    protected void mouseReleased() {
        movableCircles.release();
    }
}
