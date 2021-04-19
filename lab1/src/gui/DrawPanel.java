package gui;

import Drawings.CoordinateSpace.*;
import Drawings.graphicElements.*;
import Drawings.graphicElements.Image;
import Drawings.graphicElements.Rectangle;
import Drawings.graphicElements.Splines.*;
import Drawings.graphicElements.Splines.Spline;
import Drawings.graphicElements.Support.LineFormula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    protected Mouse mouse;
    private Color backgroundColor;

    public DrawPanel() {
        initElements();
        setTimer(10);
        setCoordinateSpace(new LinearCoordinateSpace(-100, 100, 100, -100));
        setActions();
        setup();
    }

    private void initElements() {
        elementsCapacity = 100;
        elementsAmount = 0;
        backgroundColor = Color.MAGENTA;
        elements = new Drawable[elementsCapacity];
        mouse = new Mouse();
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

    protected void setCoordinateSpace(CoordinateSpace coordinateSpace) {
        new DrawElement().setCoordinateSpace(coordinateSpace);
        this.coordinateSpace = coordinateSpace;
        setGrid();
    }

    public void setBackground(Color color) {
        if (color != null)
            this.backgroundColor = color;
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

    protected void removeElement(Drawable element) {
        for (int elem = 0; elem < elementsAmount; elem++) {
            if (elements[elem] == element) {
                while (elem < elementsAmount) {
                    elements[elem] = elements[elem + 1];
                    elem += 1;
                }
                elements[elem] = null;
                elementsAmount -= 1;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        clearScreen(g);
        countMouseCoordinates();
        draw();
        for (int elem = 0; elem < elementsAmount; elem++)
            elements[elem].draw(g);
        if (showGrid)
            this.grid.draw(g);
    }

    private void clearScreen(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void countMouseCoordinates() {
        mouse.x = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x;
        mouse.y = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().y;
        coordinateSpace.countMouseCoordinates(mouse);
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

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                drawPanel.mouseClicked();
            }
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                drawPanel.mousePressed();
            }
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                drawPanel.mouseReleased();
            }
        });
    }

    protected abstract void setup();
    protected abstract void draw();
    protected void mouseClicked() {};
    protected void mousePressed() {};
    protected void mouseReleased() {};

    protected Triangle makeTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (Triangle) makeElement(new Triangle(x1, y1, x2, y2, x3, y3));
    }

    protected Circle makeCircle(int x, int y, int radius) {
        return (Circle) makeElement(new Circle(x, y, radius));
    }
    protected Circle makeCircle(Point point, int radius) { return (Circle) makeElement(new Circle(point, radius)); }

    protected Line makeLine(int x1, int y1, int x2, int y2) { return (Line) makeElement(new Line(x1, y1, x2, y2)); }
    protected Line makeLine(Point point1, Point point2) { return (Line) makeElement(new Line(point1, point2)); }
    protected Line makeLine(LineFormula lineFormula) { return (Line) makeElement(new Line(lineFormula)); }

    protected Rectangle makeRectangle(int x, int y, int width, int height) {
        return (Rectangle) makeElement(new Rectangle(x, y, width, height));
    }

    protected Image makeImage(String filePath, int x, int y, int width, int height) {
        return (Image) makeElement(new Image(filePath, x, y, width, height));
    }

    protected Spline makeSpline() {
        return (Spline) makeElement(new Spline());
    }

    protected LineSpline makeLineSpline(Point ... points) { return (LineSpline) makeElement(new LineSpline(points)); }
    protected BSpline makeBSpline() { return (BSpline) makeElement(new BSpline()); }


    private DrawElement makeElement(DrawElement element) {
        element.setCoordinateSpace(coordinateSpace);
        addElement(element);
        return element;
    }
}
