package windows.Exercises.lab2.Excercise2_10;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Dimension2d.Circle;
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
        setBackgroundColor(Color.white);
        pointsAmount = 3;
        makeMovableCircles(pointsAmount);
        lineSpline = makeLineSpline(movableCircles.getPoints());
        buildSpline();
    }

    public void setData(int pointsAmount, int k) {
        setPointsAmount(pointsAmount);
        setK(k);
    }

    public void setPointsAmount(int pointsAmount) {
        if (pointsAmount == movableCircles.circles.length + 1) {
            int c = 0;
            Circle[] circles = new Circle[pointsAmount];
            while (c < movableCircles.circles.length) {
                circles[c] = movableCircles.circles[c];
                c++;
            }
            circles[c] = makeCircle(circles[c - 1].getCenter().x + size / 20, circles[c - 1].getCenter().y, size / 50);
            movableCircles.circles = circles;
            movableCircles.circlesAmount = pointsAmount;
        } else if (pointsAmount == movableCircles.circles.length - 1) {
            int c = 0;
            Circle[] circles = new Circle[pointsAmount];
            while (c < pointsAmount) {
                circles[c] = movableCircles.circles[c];
                c++;
            }
            removeElement(movableCircles.circles[c]);
            movableCircles.circles = circles;
            movableCircles.circlesAmount = pointsAmount;
        } else {
            for (Circle circle : movableCircles.circles)
                removeElement(circle);
            makeMovableCircles(pointsAmount);
        }
        lineSpline.setPoints(movableCircles.getPoints());
        bSpline.setPoints(movableCircles.getPoints());
    }

    public void setK(int k) {
        bSpline.setK(k);
        bSpline.setPoints(movableCircles.getPoints());
    }

    private void buildCoordinateSpace() {
        size = 10000;
        setCoordinateSpace(new LinearCoordinateSpace(-size, size, -size, size));
        setBackgroundColor(Color.white);
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
