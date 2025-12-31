package windows.Exercises.lab4.Krugaser;

import Drawings.graphicElements.Dimension2d.Circle;
import Drawings.graphicElements.Support.Point;

public class Krugaser {

    private Point movingTo;
    private Point center;
    private Circle[] circles;
    private float rotation = 0;
    public float rotationSpeed = 0.05f;
    public float rotationRadius = 50;

    public Krugaser(Circle[] circles) {
        movingTo = new Point();
        center = new Point();
        setCircles(circles);
    }

    public void draw() {
        move();
        rotate();
        configureCircles();
    }

    public void setCircles(Circle[] circles) {
        if (circles != null) {
            this.circles = circles;
        } else {
            this.circles = new Circle[] {};
        }
        configureCircles();
    }

    public void setMovingToPoint(Point to) {
        this.movingTo = to;
    }

    private void move() {
        if (center.x < movingTo.x)
            center.add(1, 0, 0);
        else if (center.x > movingTo.x)
            center.add(-1, 0, 0);
        if (center.y < movingTo.y)
            center.add(0, 1, 0);
        else if (center.y > movingTo.y)
            center.add(0, -1, 0);
    }

    private void rotate() {
        rotation += rotationSpeed;
        if (rotation >= 2 * Math.PI)
            rotation = 0;
    }

    private void configureCircles() {
        int circlesAmount = circles.length;
        Point starter = new Point(center);
        starter.add(new Point(rotationRadius, 0));
        for (int circleNumber = 0; circleNumber < circlesAmount; circleNumber++) {
            circles[circleNumber].setCenter(starter);
            circles[circleNumber].rotate(center, (float) (rotation + 2 * Math.PI / circlesAmount * circleNumber));
            circles[circleNumber].setRadius(Point.length(movingTo, circles[circleNumber].getCenter()) / 10 + 10);
        }
    }
}
