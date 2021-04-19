package windows.Exercises.lab2.Excercise2_10;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Circle;
import Drawings.graphicElements.Splines.*;
import gui.DrawPanel;
import windows.Exercises.lab2.Helpers.MovableCircles;

import java.awt.*;

public class Sketch extends DrawPanel {

    int size;
    BSpline bSpline;
    LineSpline lineSpline;
    int pointsAmount;
    MovableCircles movableCircles;

    @Override
    protected void setup() {
        buildCoordinateSpace();
        setBackground(Color.white);
        pointsAmount = 4;
        makeMovableCircles(pointsAmount);
        lineSpline = makeLineSpline(movableCircles.getPoints());
        buildSpline();

        setData(7, 3);
    }

    public void setData(int pointsAmount, int k) {
        for (Circle circle : movableCircles.circles)
            removeElement(circle);
        makeMovableCircles(pointsAmount);
        lineSpline.setPoints(movableCircles.getPoints());
        bSpline.setPoints(movableCircles.getPoints());
        bSpline.setK(k);
    }

    private void buildCoordinateSpace() {
        size = 10000;
        setCoordinateSpace(new LinearCoordinateSpace(-size, size, -size, size));
        setBackground(Color.white);
    }

    private void buildSpline() {
        bSpline = makeBSpline();
        bSpline.setColor(Color.red);
        bSpline.precision = 10.0 / size;
        bSpline.setPoints(movableCircles.getPoints());
    }

    private void makeMovableCircles(int pointsAmount) {
        movableCircles = new MovableCircles(pointsAmount);
        for (int c = 0; c < pointsAmount; c++)
            movableCircles.setCircle(c, makeCircle((size * 2) / pointsAmount * c - (int) (size * 0.9), 0, size / 50));
    }

    @Override
    protected void draw() {
        if (movableCircles.circleIsMoving()) {
            lineSpline.setPoints(movableCircles.getPoints());
            bSpline.setPoints(movableCircles.getPoints());
        }
        movableCircles.moveChosenCircle(mouse);
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
