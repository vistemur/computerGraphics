package windows.Exercises.Exercise3;

import Drawings.graphicElements.*;
import Matrix.Matrix;
import gui.DrawPanel;

public class Sketch3 extends DrawPanel {

    Triangle triangle;
    Line line;

    @Override
    protected void setup() {
        showGrid = true;
        triangle = makeTriangle(0, 0, 10, 10, 10, 0);
        line = makeLine(0, 50, 50, 0);
    }

    @Override
    protected void draw() {

    }

    @Override
    protected void mousePressed() {
        triangle.setMatrix(Matrix.multiply(triangle.getMatrix(), new int[][] {{1, 1}, {-1, 1}}));
    }
}
