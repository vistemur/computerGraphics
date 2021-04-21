package windows.Exercises.lab1.ABCDMatrixTest;

import Drawings.graphicElements.Triangle;
import Matrix.Matrix;
import gui.DrawPanel;

public class ABCDMatrixSketch extends DrawPanel {

    Triangle triangle;

    @Override
    protected void setup() {
        showGrid = true;
        triangle = makeTriangle(-10, -10, 0, 10, 10, -10);
    }

    @Override
    protected void draw() {}

    public void applyMatrix(double[][] matrix) {
        triangle.setMatrix(Matrix.multiply(triangle.getMatrix(), matrix));
    }

    public void reset() {
        triangle.setPoints(-10, -10, 0, 10, 10, -10);
    }
}
