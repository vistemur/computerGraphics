package windows.Exercises.Exercise2;

import Drawings.graphicElements.Triangle;
import Matrix.Matrix;
import gui.DrawPanel;

public class Sketch2 extends DrawPanel {

    Triangle triangle;

    @Override
    protected void setup() {
        showGrid = true;
        triangle = makeTriangle(0, 0, 20, 20, 0, 20);
    }

    @Override
    protected void draw() {
    }

    @Override
    protected void mousePressed() {
        triangle.setMatrix(Matrix.multiply(triangle.getMatrix(), new int[][] {{0, 1}, {-1, 0}}));
    }
}
