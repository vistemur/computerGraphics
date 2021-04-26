package windows.Exercises.lab2.Helpers;

import Drawings.CoordinateSpace.Mouse;
import Drawings.graphicElements.Dimension2d.Circle;

import java.awt.*;
import java.awt.geom.Point2D;
import Drawings.graphicElements.Support.Point;


public class MovableCircles {

    public int circlesAmount;
    public Circle[] circles;
    private Circle hoveredCircle;
    private Circle chosenCircle;
    private Color defaultColor = Color.blue;
    private Color pickColor = Color.yellow;
    private Color hoverColor = Color.gray;

    public MovableCircles(int circlesAmount) {
        this.circlesAmount = circlesAmount;
        circles = new Circle[circlesAmount];
    }

    public void setCircle(int circleNumber, Circle circle) {
        if (circleNumber >= 0 && circleNumber < circlesAmount) {
            circles[circleNumber] = circle;
            circles[circleNumber].setColor(defaultColor);
        }
    }

    public Circle getCircle(int circleNumber) {
        if (circleNumber >= 0 && circleNumber < circlesAmount)
            return circles[circleNumber];
        return null;
    }

    public Point[] getPoints() {
        Point[] points = new Point[circlesAmount];
        for (int c = 0; c < circlesAmount; c++)
            points[c] = circles[c].getCenter();
        return points;
    }

    public boolean circleIsMoving() {
        return chosenCircle != null;
    }

    public void moveChosenCircle(Mouse mouse) {
        if (chosenCircle != null) {
            chosenCircle.setCenter(mouse.getPoint());
        } else {
            if (hoveredCircle == null) {
                hoveredCircle = getHoveredMovableCircle(mouse);
                if (hoveredCircle != null)
                    hoveredCircle.setColor(hoverColor);
            } else if (hoveredCircle != getHoveredMovableCircle(mouse)) {
                hoveredCircle.setColor(defaultColor);
                hoveredCircle = null;
            }
        }
    }

    private Circle getHoveredMovableCircle(Mouse mouse) {
        for (Circle movableCircle : circles)
            if (Point2D.distance(   movableCircle.getCenter().x, movableCircle.getCenter().y,
                    mouse.x, mouse.y) <= movableCircle.getRadius())
                return  movableCircle;
        return null;
    }

    public void pick(Mouse mouse) {
        if (chosenCircle == null) {
            chosenCircle = getHoveredMovableCircle(mouse);
            if (chosenCircle != null)
                chosenCircle.setColor(pickColor);
        }
    }

    public void release() {
        if (chosenCircle != null) {
            chosenCircle.setColor(defaultColor);
            chosenCircle = null;
        }
    }
}
