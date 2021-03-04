package gui;

import Drawings.CoordinateSpace.CoordinateSpace;
import Drawings.CoordinateSpace.LinearCoordinateSpace;
import Drawings.graphicElements.Circle;
import Drawings.graphicElements.Drawable;
import Drawings.graphicElements.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;
import java.util.TimerTask;

public abstract class DrawPanel extends JPanel {
    private Timer timer;
    private int elementsAmount;
    private int elementsCapacity;
    private Drawable[] elements;
    private CoordinateSpace coordinateSpace;
    private DrawPanel drawPanel = this;
    protected boolean showGrid = false;
    private Drawable grid;

    public DrawPanel() {
        initElements();
        setTimer(10);
        setCoordinateSpace();
        setActions();
        setup();
    }

    private void initElements() {
        elementsCapacity = 100;
        elementsAmount = 0;
        elements = new Drawable[elementsCapacity];
    }

    private void setTimer(int period) {
        DrawPanel drawing = this;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawing.repaint();
            }
        }, 0, period);
    }

    private void setCoordinateSpace() {
        this.coordinateSpace = new LinearCoordinateSpace(-100, 100, 100, -100);
        setGrid();
    }

    private void setGrid()  {
        this.grid = coordinateSpace.getGrid();
        grid.setCoordinateSpace(coordinateSpace);
    }

    protected void addElement(Drawable element) {
        if (elementsAmount == elementsCapacity) {
            elementsCapacity *= 2;
            Drawable[] newElements = new Drawable[elementsCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        } else {
            elements[elementsAmount] = element;
            elementsAmount++;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        draw();
        for (int elem = 0; elem < elementsAmount; elem++)
            elements[elem].draw(g);
        if (showGrid)
            this.grid.draw(g);
    }

    private void setActions() {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                coordinateSpace.setReal(drawPanel.getWidth(), drawPanel.getHeight());
                for (int elem = 0; elem < elementsAmount; elem++)
                    elements[elem].countDrawCoordinates();
                if (showGrid)
                    grid.countDrawCoordinates();
            }
        });
    }

    protected abstract void setup();
    protected abstract void draw();

    protected Triangle makeTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        Triangle triangle;

        triangle = new Triangle(x1, y1, x2, y2, x3, y3);
        triangle.setCoordinateSpace(coordinateSpace);
        addElement(triangle);
        return triangle;
    }

    protected Circle makeCircle(int x, int y, int radius) {
        Circle circle;

        circle = new Circle(x, y, radius);
        circle.setCoordinateSpace(coordinateSpace);
        addElement(circle);
        return circle;
    }
}
