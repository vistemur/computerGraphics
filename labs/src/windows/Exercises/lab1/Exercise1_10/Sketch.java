package windows.Exercises.lab1.Exercise1_10;

import Drawings.CoordinateSpace.*;
import Drawings.graphicElements.Dimension2d.Circle;
import Drawings.graphicElements.Dimension2d.Line;
import gui.DrawPanel;
import Drawings.graphicElements.Support.Point;


import java.awt.*;
import java.awt.geom.Point2D;

public class Sketch extends DrawPanel {

    Circle circle1;
    Circle circle2;
    Circle circle3;
    Circle circle4;
    Line line0;
    Line line1;
    Line line2;

    enum State {makingCircle1, sizingCircle1, makingCircle2, sizingCircle2, movingCircle1, movingCircle2, normal}
    State state;

    @Override
    protected void setup() {
        setCoordinateSpace(new ConstantCoordinateSpace(-100, 100, 100, -100, 500, 500));
        showGrid = true;
        circle1 = makeCircle(0, 0, 20);
        circle2 = makeCircle(50, 30, 10);
        circle3 = makeCircle(50, 30, 20);
        circle4 = makeCircle(0, 0, 10);
        line0 = makeLine(0, 0, 10, 20);
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
        circle3.setFill(false);
        circle4.setFill(false);
    }

    private void setColors() {
        circle1.setColor(Color.cyan);
        line0.setColor(Color.blue);
        line1.setColor(Color.red);
        line2.setColor(Color.green);
    }

    private void makeEverythingInvisible() {
        circle1.setVisible(false);
        circle2.setVisible(false);
        circle3.setVisible(false);
        circle4.setVisible(false);
        line0.setVisible(false);
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

    private void countIntersectionPoints(Point circle1Center, float circle1Radius, Point circle2Center, float circle2Radius) {
        Point point0 = new Point();
        Point point1 = new Point();
        line1.setVisible(false);
        line2.setVisible(false);

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

        double x0 = -a * c / (a * a + b * b);
        double y0 = -b * c / (a * a + b * b);

        double R;
        if (circle1Radius > circle2Radius) R = circle1Radius;
        else R = circle2Radius;
        double LL = (int)Math.sqrt((circle1Center.x - circle2Center.x)*(circle1Center.x - circle2Center.x)
                + (circle1Center.y - circle2Center.y)*(circle1Center.y - circle2Center.y));

        if ((c * c > r * r * (a * a + b * b) + epsilon) && (LL > R)) {
            // точек пересечения нет
            // если это исходные две окружности, то строим для них внутренние касательные
            // если это дополнительно построенные окружности, то они не должны были сюда попасть

            float x;
            float y;
            int r3;
            r3 = (int)(0.5 * LL);

            if (circle1Radius < circle2Radius)
            {
                circle3.setCenter(circle2Center);
                circle3.setRadius(circle1Radius + circle2Radius);
                circle4.setCenter((circle1Center.x + circle2Center.x)/2, (circle1Center.y + circle2Center.y)/2);
                circle4.setRadius(r3);
                x = circle1Center.x;
                y = circle1Center.y;

                circle3.setColor(Color.gray);
                circle4.setColor(Color.white);
            }
            else
            {
                circle3.setCenter(circle1Center);
                circle3.setRadius(circle1Radius + circle2Radius);
                circle4.setCenter((circle1Center.x + circle2Center.x)/2, (circle1Center.y + circle2Center.y)/2);
                circle4.setRadius(r3);
                x = circle2Center.x;
                y = circle2Center.y;

                circle3.setColor(Color.white);
                circle4.setColor(Color.gray);
            }
            circle3.setVisible(true);
            circle4.setVisible(true);

            buildTangentLines(x, y, circle4.getCenter(), circle4.getRadius(), circle3.getCenter(), circle3.getRadius());
        }
        else if (Math.abs (c * c - r * r * (a * a + b * b)) < epsilon) {
            //	одна точка пересечения
            //	через которую и пройдёт касательная
            //  которую, надеюсь, можно построить так же, как при двух точках пересечения

            line1.setVisible(false);
            line2.setVisible(false);
            circle3.setVisible(false);
            circle4.setVisible(false);
        }
        else {
            // две точки пересечения
            // для исходных двух окружностей пишем, что внутренние касательные построить невозможно,
            // так как они пересекаются.

            line1.setVisible(false);
            line2.setVisible(false);
            circle3.setVisible(false);
            circle4.setVisible(false);

            double d = r * r - c * c / (a * a + b * b);
            double mult = Math.sqrt(d / (a * a + b * b));
            point0.x = (int) (x0 + b * mult + circle1Center.x);
            point0.y = (int) (y0 - a * mult + circle1Center.y);
            point1.x = (int) (x0 - b * mult + circle1Center.x);
            point1.y = (int) (y0 + a * mult + circle1Center.y);
        }

        line0.setPoint(0, point0);
        line0.setPoint(1, point1);
    }

    private void buildTangentLines(float x, float y, Point circle1Center, float circle1Radius, Point circle2Center, float circle2Radius) {
        Point point00 = new Point();
        Point point01 = new Point();
        Point point10 = new Point();
        Point point11 = new Point();

        double a;
        double b;
        double c;

        double r = (double) circle1Radius;
        double R = (double) circle2Radius;

        a = -2 * (circle2Center.x - circle1Center.x);
        b = -2 * (circle2Center.y - circle1Center.y);
        c = (circle2Center.x - circle1Center.x) * (circle2Center.x - circle1Center.x) +
                (circle2Center.y - circle1Center.y) * (circle2Center.y - circle1Center.y)
                + circle1Radius*circle1Radius - circle2Radius*circle2Radius;

        //double r, a, b, c; // входные данные

        double x0 = -a * c / (a * a + b * b);
        double y0 = -b * c / (a * a + b * b);

        // машинный ноль
        double epsilon = Double.longBitsToDouble(971l << 52);

        // конечное смещение точек. его надо пересчитать, потому что это - неверно
        double dx = r*R / (R+r);
        double dy = (2*r*R + r*r) / (R*R + 2*r*R + r*r);

        if (c * c > r * r * (a * a + b * b) + epsilon) {
            // точек пересечения нет
            // дополнительно построенные окружности не должны были сюда попасть
        }
        else
          //  if (Math.abs (c * c - r * r * (a * a + b * b)) < epsilon) {
            //	одна точка пересечения
            //	через которую и пройдёт касательная
        //}
        //else
        {
            // две точки пересечения
            double d = r * r - c * c / (a * a + b * b);
            double mult = Math.sqrt(d / (a * a + b * b));

            point00.x = (int) (x);
            point00.y = (int) (y);
            point01.x = (int) (x0 + b * mult + circle1Center.x);
            point01.y = (int) (y0 - a * mult + circle1Center.y);

            point10.x = (int) (x);
            point10.y = (int) (y);
            point11.x = (int) (x0 - b * mult + circle1Center.x);
            point11.y = (int) (y0 + a * mult + circle1Center.y);
        }

        line1.setPoint(0, point00);
        line1.setPoint(1, point01);
        line1.setVisible(true);
        line2.setPoint(0, point10);
        line2.setPoint(1, point11);
        line2.setVisible(true);
    }
}
