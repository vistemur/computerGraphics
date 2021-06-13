package windows.Exercises.lab4.Krugaser;

import Drawings.CoordinateSpace.ConstantCoordinateSpace;
import Drawings.graphicElements.Dimension2d.Circle;
import gui.DrawPanel;

import java.awt.*;
import java.util.Random;

public class Sketch extends DrawPanel {

    Krugaser krugaser;
    int circlesAmount;
    int circlesSize;
    Circle[] circles;
    Random random = new Random();
    Color backgroundColor;
    float r, g, b;
    float rPluser;

    private void initVariables() {
        circlesAmount = 2;
        circlesSize = 10;
    }

    @Override
    protected void setup() {
        initVariables();
        initScene();
        circles = makeCircles(circlesAmount);
        krugaser = new Krugaser(circles);
        setupBackground();
    }

    private void initScene() {
        random = new Random();
        setCoordinateSpace(new ConstantCoordinateSpace(0, 100, 0, 100, 100, 100));
    }

    private Circle[] makeCircles(int amoount) {
        circles = new Circle[amoount];
        for (int circleNumber = 0; circleNumber < amoount; circleNumber++) {
            circles[circleNumber] = makeCircle(0, 0, circlesSize);
            circles[circleNumber].setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }
        return  circles;
    }

    private void setRandomCircleColors() {
        for (Circle circle : circles)
            circle.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        krugaser.setCircles(circles);
    }

    @Override
    protected void mousePressed() {
        setRandomCircleColors();
    }

    private void setupBackground() {
        r = 0.5f;
        g = 0;
        b = 0;
        rPluser = 0.001f;
        backgroundColor = new Color(r, g, b);
    }

    private void drawBackground() {
        r += rPluser;
        backgroundColor = new Color(r, g, b);
        setBackgroundColor(backgroundColor);
        if (r >= 0.9 || r <= 0.1)
            rPluser *= -1;
    }

    @Override
    protected void draw() {
        drawBackground();
        krugaser.setMovingToPoint(mouse.getPoint());
        krugaser.draw();
    }
}
