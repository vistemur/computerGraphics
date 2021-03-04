package windows.Уxercises.Excercise1;

import Drawings.graphicElements.Circle;
import Drawings.graphicElements.Triangle;
import Matrix.Matrix;
import gui.DrawPanel;

import java.awt.*;

public class Sketch1 extends DrawPanel {

    Triangle triangle;
    Circle circle;

    boolean visibility = true;
    int x = 0;
    int move = 1;

    @Override
    protected void setup() {
        showGrid = true;
        triangle = makeTriangle(0, 0, 0, 100, 100, 100);
        circle = makeCircle(0, 0, 20);
        circle.setColor(Color.ORANGE);
    }

    @Override
    protected void draw() {
        visibility = !visibility;
        triangle.setVisible(visibility);
        circle.setColor(new Color((float) Math.random() % 1, (float) Math.random() % 1, (float) Math.random() % 1));
        circle.setCenter(x, 0);
        if (move > 0 && x >= 80)
            move = -1;
        else if (move < 0 && x <= -80)
            move = 1;
        x += move;
    }
}
