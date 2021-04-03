package Drawings.graphicElements;

import Drawings.graphicElements.Support.LineFormula;

import java.awt.*;

public class Line extends DrawElement {

    private boolean infinite = false;
    private int[][] infPoints = new int[2][2];

    public Line(int x1, int y1, int x2, int y2) {
        setPoints(x1, y1, x2, y2);
    }

    public Line(Point point1, Point point2) {
        setPoints(point1, point2);
    }

    public Line(LineFormula lineFormula) {
        setFormula(lineFormula);
    }

    public void setPoints(Point point1, Point point2) {
        setPoints(point1.x, point1.y, point2.x, point2.y);
        countDrawCoordinates();
    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        points = new int[][] {{x1, x2}, {y1, y2}};
        countDrawCoordinates();
    }

    public void setFormula(LineFormula lineFormula) {
        if (lineFormula.b == 0) {
            setPoints((int) lineFormula.countX(0), 0, (int) lineFormula.countX(10), 10);
        } else {
            setPoints(0, (int) lineFormula.countY(0), 10, (int) lineFormula.countY(10));
        }
        setInfinite(true);
    }

    public Point getPoint(int pointNumber) {
        Point point = new Point();

        if (pointNumber > -1 && pointNumber < 2) {
            point.x = points[0][pointNumber];
            point.y = points[1][pointNumber];
        }
        return point;
    }

    public void setPoint(int pointNumber, Point point) {
        if (pointNumber > -1 && pointNumber < 2) {
            points[0][pointNumber] = point.x;
            points[1][pointNumber] = point.y;
            countDrawCoordinates();
        }
    }

    public void makePerpendicularTo(Line line, Point through) {
        LineFormula formula = line.getFormula();
        formula.makePerpendicular(through);
        setFormula(formula);
    }

    public Point getMirroredPoint(Point refPoint) {
        Point answer;
        LineFormula perpendicular;

        perpendicular = getFormula();
        perpendicular.makePerpendicular(refPoint);
        answer = perpendicular.getIntersectionPoint(getFormula());
        if (perpendicular.b == 0) {
            answer.y += answer.y - refPoint.y;
            answer.x = (int) perpendicular.countX(answer.y);
        } else {
            answer.x += answer.x - refPoint.x;
            answer.y = (int) perpendicular.countY(answer.x);
        }
        return answer;
    }

    public void setInfinite(boolean infinite) {
        if (infinite != this.infinite) {
            this.infinite = infinite;
            if (infinite)
                setInfinitePoints();
        }
    }

    @Override
    public void countDrawCoordinates() {
        if (points != null && coordinateSpace != null) {
            if (infinite)
                setInfinitePoints();
            countCoordinates();
        }
    }

    private void countCoordinates() {
        if (infinite)
            countInfinitePoints();
        else
            countUserCoordinates();
    }

    private void countInfinitePoints() {
        drawPoints = coordinateSpace.convert(infPoints);
    }

    private void countUserCoordinates() {
        drawPoints = coordinateSpace.convert(points);
    }

    private void setInfinitePoints() {
        int[] space = coordinateSpace.getUserData();
        if (points[0][0] == points[0][1])
            countInfVertical(space[2], space[3]);
        else if (points[1][0] == points[1][1])
            countInfHorizontal(space[0], space[1]);
        else
            countInfPoints(getFormula(), space[2], space[3]);
        countInfinitePoints();
    }

    private void countInfVertical(int min, int max) {
        infPoints[0][0] = points[0][0];
        infPoints[1][0] = min;
        infPoints[0][1] = points[0][1];
        infPoints[1][1] = max;
    }

    private void countInfHorizontal(int min, int max) {
        infPoints[0][0] = min;
        infPoints[1][0] = points[1][0];
        infPoints[0][1] = max;
        infPoints[1][1] = points[1][1];
    }

    private void countInfPoints(LineFormula formula, int min, int max) {
        infPoints[0][0] = (int) formula.countX(min);
        infPoints[1][0] = min;
        infPoints[0][1] = (int) formula.countX(max);
        infPoints[1][1] = max;
    }

    public LineFormula getFormula() {
        return new LineFormula(getPoint(0), getPoint(1));
    }

    protected void display(Graphics g) {
        g.drawLine(drawPoints[0][0], drawPoints[1][0], drawPoints[0][1], drawPoints[1][1]);
    }
}
