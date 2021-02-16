package gui;

import graphicElements.Drawable;
import graphicElements.Triangle;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class DrawPanel extends JPanel {
    private Timer timer;
    int elementsAmount;
    int elementsCapacity;
    private Drawable[] elements;

    public DrawPanel() {
        elementsCapacity = 100;
        elementsAmount = 0;
        elements = new Drawable[elementsCapacity];
        DrawPanel drawing = this;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawing.repaint();
            }
        }, 0, 10);
        setup();
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
    }

    protected abstract void setup();
    protected abstract void draw();

    protected Triangle makeTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        Triangle triangle;

        triangle = new Triangle(x1, y1, x2, y2, x3, y3);
        addElement(triangle);
        return triangle;
    }
}
