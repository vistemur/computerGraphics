package windows.Exercises.lab1.Exercise1_02;

import Drawings.graphicElements.Dimension2d.Circle;
import Drawings.graphicElements.Dimension2d.Line;
import Drawings.graphicElements.Dimension2d.Triangle;
import gui.DrawPanel;

import java.awt.geom.Point2D;

public class Sketch extends DrawPanel {

    Triangle triangle1, triangle2;
    Line line;
    Circle[] movableCircles;
    Circle chosenCircle = null;

    @Override
    protected void setup() {
        showGrid = true;
        triangle1 = makeTriangle(-20, -10, -10, -20, -20, -20);
        triangle2 = makeTriangle(20, 10, 10, 20, 20, 20);
        line = makeLine(-20, 20, 20, -20);
        line.setInfinite(true);

        makeMovableCircles();
        mirrorTriangle(triangle1);
    }

    private void makeMovableCircles() {
        movableCircles = new Circle[8];
        for (int c = 0; c < movableCircles.length; c++)
            if (c < 2)
                movableCircles[c] = makeCircle(line.getPoint(c), 2);
            else if (c < 5)
                movableCircles[c] = makeCircle(triangle1.getPoint(c - 2), 2);
            else
                movableCircles[c] = makeCircle(triangle2.getPoint(c - 5), 2);
    }

    private void mirrorTriangle(Triangle triangle) {
        Triangle mirrorTriangle;
        int circlePluser;

        if (triangle == triangle1) {
            mirrorTriangle = triangle2;
            circlePluser = 5;
        } else {
            mirrorTriangle = triangle1;
            circlePluser = 2;
        }
        for (int c = 0; c < 3; c++) {
            mirrorTriangle.setPoint(c, line.getMirroredPoint(triangle.getPoint(c)));
            movableCircles[c + circlePluser].setCenter(mirrorTriangle.getPoint(c));
        }
    }

    @Override
    protected void draw() {
        if (chosenCircle != null) {
            chosenCircle.setCenter(mouse.getPoint());
            for (int c = 0; c < movableCircles.length; c++) {
                if (movableCircles[c] == chosenCircle) {
                    if (c < 2) {
                        line.setPoint(c, mouse.getPoint());
                        mirrorTriangle(triangle1);
                    } else if (c < 5) {
                        triangle1.setPoint(c - 2, mouse.getPoint());
                        triangle2.setPoint(c - 2, line.getMirroredPoint(mouse.getPoint()));
                        movableCircles[c + 3].setCenter(triangle2.getPoint(c - 2));
                    } else {
                        triangle2.setPoint(c - 5, mouse.getPoint());
                        triangle1.setPoint(c - 5, line.getMirroredPoint(mouse.getPoint()));
                        movableCircles[c - 3].setCenter(triangle1.getPoint(c - 5));
                    }
                    break;
                }
            }
        }
    }

    @Override
    protected void mousePressed() {
        if (chosenCircle == null) {
            for (Circle movableCircle : movableCircles) {
                if (Point2D.distance(   movableCircle.getCenter().x, movableCircle.getCenter().y,
                                        mouse.x, mouse.y) <= movableCircle.getRadius()) {
                    chosenCircle = movableCircle;
                    break;
                }
            }
        }
    }

    @Override
    protected void mouseReleased() {
        if (chosenCircle != null)
            chosenCircle = null;
    }
}
