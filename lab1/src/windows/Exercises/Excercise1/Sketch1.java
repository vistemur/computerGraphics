package windows.Exercises.Excercise1;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.*;
import gui.DrawPanel;

import java.awt.*;
import java.awt.geom.Point2D;

public class Sketch1 extends DrawPanel {

    Circle circle1;
    Circle circle2;
    Line line1;
    Line line2;

    enum State {makingCircle1, sizingCircle1, makingCircle2, sizingCircle2, movingCircle1, movingCircle2, normal}
    State state;

    @Override
    protected void setup() {
        showGrid = true;
        circle2 = makeCircle(50, 30, 10);
        circle1 = makeCircle(0, 0, 20);
        line1 = makeLine(0, 0, 50, 30);
        line2 = makeLine(0, 0, 30, 50);
        state = State.makingCircle1;
        setColors();
        disableFill();
        makeEverythingInvisible();
    }

    private void disableFill() {
        circle1.setFill(false);
        circle2.setFill(false);
    }

    private void setColors() {
        circle1.setColor(Color.cyan);
        line1.setColor(Color.red);
        line2.setColor(Color.green);
    }

    private void makeEverythingInvisible() {
        circle1.setVisible(false);
        circle2.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
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
            case movingCircle1:
                circle1.setCenter(mouse.getPoint());
                countLinePoints();
                break;
            case movingCircle2:
                circle2.setCenter(mouse.getPoint());
                countLinePoints();
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
            case normal:
                if (Point2D.distance(   circle1.getCenter().x, circle1.getCenter().y,
                                        mouse.getPoint().x, mouse.getPoint().y)
                                                    <=
                                        circle1.getRadius()) {
                    state = State.movingCircle1;
                } else if (Point2D.distance(    circle2.getCenter().x, circle2.getCenter().y,
                                                mouse.getPoint().x, mouse.getPoint().y)
                                                         <=
                                                circle2.getRadius()) {
                    state = State.movingCircle2;
                }
                break;
            case movingCircle1: case movingCircle2:
                state = State.normal;
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
                countLinePoints();
                line1.setVisible(true);
                state = State.normal;
                break;
        }
    }

    private void countLinePoints() {
        countIntersectionPoints(circle1.getCenter(), circle1.getRadius(),
                circle2.getCenter(), circle2.getRadius());
    }

    private void countIntersectionPoints(Point circle1Center, int circle1Radius, Point circle2Center, int circle2Radius) {
        Point point00 = new Point();
        Point point01 = new Point();
        Point point10 = new Point();
        Point point11 = new Point();

        /// x2 + y2 = r12
        //(x - x2)2 + (y - y2)2 = r22
        //Вычтем из второго уравнения первое, чтобы избавиться от квадратов переменных:
        //x2 + y2 = r12
        //x (-2x2) + y (-2y2) + (x22 + y22 + r12 - r22) = 0
        //Таким образом, мы свели задачу о пересечении двух окружностей к задаче о пересечении первой окружности и следующей прямой:
        //Ax + By + C = 0,
        //A = -2x2,
        //B = -2y2,
        //C = x22 + y22 + r12 - r22.

        double a = -2 * (circle2Center.x - circle1Center.x);
        double b = -2 * (circle2Center.y - circle1Center.y);
        double c = (circle2Center.x - circle1Center.x) * (circle2Center.x - circle1Center.x) +
                (circle2Center.y - circle1Center.y) * (circle2Center.y - circle1Center.y)
                + circle1Radius*circle1Radius - circle2Radius*circle2Radius;
        double r = circle1Radius;
        double epsilon = Double.longBitsToDouble(971l << 52); // машинный ноль

        //double r, a, b, c; // входные данные

        double x0 = -a*c / (a*a + b*b);
        double y0 = -b*c / (a*a + b*b);
        if (c*c > r*r * (a*a + b*b) + epsilon) {
            // точек пересечения нет
            // если это исходные две окружности, то строим для них внутренние касательные
            // если это дополнительно построенные окружности, то они не должны были сюда попасть
        }
        else if (Math.abs (c*c - r*r * (a*a + b*b)) < epsilon) {
            //	одна точка пересечения
            //	через которую и пройдёт касательная
            //  которую, надеюсь, можно построить так же, как при двух точках пересечения
        }
        else {
            // две точки пересечения
            // для исходных двух окружностей пишем, что внутренние касательные построить невозможно,
            // так как они пересекаются. для дополнительно построенных ищем точки.
        	double d = r*r - c*c / (a*a + b*b);
        	double mult = Math.sqrt(d / (a*a + b*b));
            point00.x = (int) (x0 + b * mult + circle1Center.x);
            point00.y = (int) (y0 - a * mult + circle1Center.y);
            point01.x = (int) (x0 - b * mult + circle1Center.x);
            point01.y = (int) (y0 + a * mult + circle1Center.y);
        }

        line1.setPoint(0, point00);
        line1.setPoint(1, point01);
        line2.setPoint(0, point10);
        line2.setPoint(1, point11);
        //line2.setVisible(true);
    }
}
