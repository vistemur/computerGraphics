package gui;

import Drawings.CoordinateSpace.*;
import Drawings.graphicElements.*;
import Drawings.graphicElements.Dimension2d.*;
import Drawings.graphicElements.Dimension2d.Image;
import Drawings.graphicElements.Dimension2d.Rectangle;
import Drawings.graphicElements.Dimension3d.*;
import Drawings.graphicElements.Splines.*;
import Drawings.graphicElements.Splines.Spline;
import Drawings.graphicElements.Support.LineFormula2d;
import Drawings.graphicElements.Support.Point;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class DrawPanel extends JPanel {
    private Timer timer;
    private int elementsAmount;
    private int elementsCapacity;
    private Drawable[] elements;
    private CoordinateSpace coordinateSpace;
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

    public void setBackgroundColor(Color color) {
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
                coordinateSpace.setReal(DrawPanel.this.getWidth(), DrawPanel.this.getHeight());
                for (int elem = 0; elem < elementsAmount; elem++)
                    elements[elem].countDrawCoordinates();
                if (showGrid)
                    grid.countDrawCoordinates();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DrawPanel.this.mouseClicked();
            }
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                DrawPanel.this.mousePressed();
            }
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                DrawPanel.this.mouseReleased();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                switch (e.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        DrawPanel.this.keyPressed(e);
                        break;
                    case KeyEvent.KEY_RELEASED:
                        DrawPanel.this.keyReleased(e);
                        break;
                }
                return false;
            }
        });
    }

    protected abstract void setup();
    protected abstract void draw();
    protected void mouseClicked() {}
    protected void mousePressed() {}
    protected void mouseReleased() {}
    protected void keyPressed(KeyEvent e) {}
    protected void keyReleased(KeyEvent e) {}

    // needs to be optimised
    protected Triangle makeTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (Triangle) makeElement(new Triangle(x1, y1, x2, y2, x3, y3));
    }

    protected Circle makeCircle(float x, float y, int radius) {
        return (Circle) makeElement(new Circle(x, y, radius));
    }
    protected Circle makeCircle(Point point, int radius) { return (Circle) makeElement(new Circle(point, radius)); }

    protected Line makeLine(int x1, int y1, int x2, int y2) { return (Line) makeElement(new Line(x1, y1, x2, y2)); }
    protected Line makeLine(Point point1, Point point2) { return (Line) makeElement(new Line(point1, point2)); }
    protected Line makeLine(LineFormula2d lineFormula2d) { return (Line) makeElement(new Line(lineFormula2d)); }

    protected Rectangle makeRectangle(int x, int y, int width, int height) {
        return (Rectangle) makeElement(new Rectangle(x, y, width, height));
    }

    protected Image makeImage(String filePath, int x, int y, int width, int height) {
        return (Image) makeElement(new Image(filePath, x, y, width, height));
    }

    protected Spline makeSpline() { return (Spline) makeElement(new Spline()); }
    protected LineSpline makeLineSpline(Point ... points) { return (LineSpline) makeElement(new LineSpline(points)); }
    protected BSpline makeBSpline() { return (BSpline) makeElement(new BSpline()); }

    protected Triangle3d makeTriangle3d() { return (Triangle3d) makeElement(new Triangle3d()); }
    protected Rectangle3d makeRectangle3d() { return (Rectangle3d) makeElement(new Rectangle3d()); }
    protected Cube makeCube() { return (Cube) makeElement(new Cube()); }
    protected Surface makeSurface() { return (Surface) makeElement(new Surface()); }
    protected Surface makeSphere() { return (Sphere) makeElement(new Sphere()); }

    private Drawable makeElement(Drawable element) {
        element.setCoordinateSpace(coordinateSpace);
        addElement(element);
        return element;
    }
}
