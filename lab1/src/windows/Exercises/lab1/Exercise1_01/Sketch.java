package windows.Exercises.lab1.Exercise1_01;

import Drawings.CoordinateSpace.ConstantCoordinateSpace;
import Drawings.graphicElements.*;
import Matrix.Matrix;
import gui.DrawPanel;
import Drawings.graphicElements.Support.Point;


public class Sketch extends DrawPanel {

    enum State {SettingPoints, normal}
    State state;
    int pointSetting;
    Point[] points;
    Line[] lines;
    Triangle triangle;

    @Override
    protected void setup() {
        setCoordinateSpace(new ConstantCoordinateSpace(-100, 100, 100, -100, 500, 500));
        pointSetting = 0;
        state = State.SettingPoints;
        showGrid = true;
        points = new Point[3];
        initTriangle();
        initLines();
    }

    private void initTriangle() {
        triangle = makeTriangle(0, 0, 20, 20, 0, 20);
        triangle.setVisible(false);
    }

    private void initLines() {
        lines = new Line[3];
        for (int c = 0; c < lines.length; c++) {
            lines[c] = makeLine(0, 0, 0, 0);
            lines[c].setVisible(false);
        }
    }

    @Override
    protected void draw() {
        if (state == State.SettingPoints && pointSetting > 0 && pointSetting < 3) {
            lines[pointSetting - 1].setPoints(points[pointSetting - 1], new Point(mouse.x, mouse.y));
            if (pointSetting == 2)
                lines[pointSetting].setPoints(points[0], new Point(mouse.x, mouse.y));
        }
    }

    @Override
    protected void mousePressed() {
        switch (state) {
            case SettingPoints -> setPoint();
            case normal -> triangle.setMatrix(Matrix.multiply(triangle.getMatrix(), new float[][] {{0, 1}, {-1, 0}}));
        }
    }

    private void setPoint() {
        points[pointSetting++] = new Point(mouse.x, mouse.y);
        if (pointSetting > 1) {
            lines[pointSetting - 2].setPoints(points[pointSetting - 1], points[pointSetting - 2]);
            if (pointSetting - 1 < lines.length)
                lines[pointSetting - 1].setVisible(true);
            if (pointSetting == 2)
                lines[pointSetting].setVisible(true);
        } else
            lines[0].setVisible(true);
        if (pointSetting == 3)
            translateToNormalState();
    }

    private void translateToNormalState() {
        triangle.setPoints(points[0], points[1], points[2]);
        for (Line line : lines)
            line.setVisible(false);
        triangle.setVisible(true);
        state = State.normal;
    }
}
