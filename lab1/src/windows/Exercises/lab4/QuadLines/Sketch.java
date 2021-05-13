package windows.Exercises.lab4.QuadLines;

import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Dimension2d.Line;
import Drawings.graphicElements.Dimension2d.Rectangle;
import Drawings.graphicElements.Support.Point;
import gui.DrawPanel;

import java.awt.*;
import java.util.Random;

public class Sketch extends DrawPanel {

    enum lineToRectanglePosition {outside, inside, crosses}

    Random random;
    int x, y, w, h;
    Rectangle rectangle;
    Line[] lines;
    int linesAmount;

    @Override
    protected void setup() {
        setCoordinateSpace(new LinearCoordinateSpace(-100, 100, -100, 100));
        x = -50;
        y = -50;
        w = 100;
        h = 100;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("w = " + w);
        System.out.println("h = " + h);
        linesAmount = 3;
        random = new Random();
        setBackgroundColor(new Color(238, 238, 238));
        showGrid = true;
        rectangle = makeRectangle(x, y + h, w, h);
        rectangle.setColor(new Color(100, 50, 150));
        generateLines();
    }

    private void generateLines() {
        if (lines == null)
            initializeLines();
        else
            generateLinesPoints();
    }

    private void initializeLines() {
        lines = new Line[linesAmount];
        for (int lineNumber = 0; lineNumber < linesAmount; lineNumber++)
            lines[lineNumber] = makeLine(new Point(), new Point());
        generateLinesPoints();
    }

    private void generateLinesPoints() {
        for (var line : lines) {
            line.setPoints(getRandomPoint(), getRandomPoint());
            switch (lineToRectangle(line, rectangle)) {
                case outside -> line.setColor(Color.red);
                case inside -> line.setColor(Color.green);
                default -> line.setColor(Color.yellow);
            }
        }
    }

    private Point getRandomPoint() {
        return new Point(random.nextInt(200) - 100, random.nextInt(200) - 100);
    }

    @Override
    protected void draw() {

    }

    @Override
    protected void mouseClicked() {
        generateLinesPoints();
    }

    private lineToRectanglePosition lineToRectangle(Line line, Rectangle rectangle) {
        Point point1, point2;
        var result = lineToRectanglePosition.crosses;

        point1 = line.getPoint(0);
        point2 = line.getPoint(1);
        System.out.println(point1 + " " + point2);
        if (    (point1.x < x && point2.x < x)          ||
                (point1.x > x + w && point2.x > x + w)  ||
                (point1.y < y && point2.y < y)          ||
                (point1.y > y + h && point2.y > y + h)) {
            result =  lineToRectanglePosition.outside;
        } else if ( (point1.x <= x || point1.x >= x + w)  ||
                    (point2.x <= x || point2.x >= x + w)  ||
                    (point1.y <= y || point1.y >= y + h)  ||
                    (point2.y <= y || point2.y >= y + h)) {
            result =  lineToRectanglePosition.crosses;
        } else {
            result =  lineToRectanglePosition.inside;
        }
        System.out.println(result);
        return result;
    }
}
