package windows.Exercises.lab3.Exercise3_1;

import Drawings.graphicElements.Support.Point3d;
import windows.Exercises.Excercise;
import windows.Exercises.lab3.Exercise3_1.Panels.PointPanel;
import windows.Exercises.lab3.Exercise3_1.Panels.XYZPanel;

import javax.swing.*;
import java.awt.*;

public class Exercise3_1 extends Excercise {

    SurfaceDataController surfaceDataController;
    Sketch sketch;
    JPanel leftPanel, rightPanel;
    int pointAmount = 4;
    XYZPanel xyzPanel;
    PointPanel[] pointPanels;

    @Override
    protected void initialize() {
        PointsPanelDataSetter pointsPanelDataSetter = new PointsPanelDataSetter();
        SketchPointsDataSetter sketchPointsDataSetter = new SketchPointsDataSetter();
        surfaceDataController = new SurfaceDataController(pointsPanelDataSetter, sketchPointsDataSetter);
        sketch = new Sketch();
        sketch.setDataSource(surfaceDataController);
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        xyzPanel = new XYZPanel("X", "Y", "Z");
        Point3d[] points = sketch.getBuildingPoints();
        pointPanels = new PointPanel[pointAmount];
        for (int pointPanelNumber = 0; pointPanelNumber < pointAmount; pointPanelNumber++) {
            pointPanels[pointPanelNumber] = new PointPanel(surfaceDataController);
            pointPanels[pointPanelNumber].setPoint(points[pointPanelNumber]);
        }
        setColoredLayout();
    }

    @Override
    protected void setColoredLayout() {
        super.setColoredLayout();
        leftPanel.setBackground(Color.BLUE);
        rightPanel.setBackground(Color.YELLOW);
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.add(leftPanel, BorderLayout.LINE_START);
        panel.add(sketch, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.LINE_END);
        rightPanel.add(xyzPanel);
        for (var pointPanel : pointPanels)
            rightPanel.add(pointPanel);
    }

    @Override
    protected void resizeElements(int width, int height) {
        Dimension controlPanelSize = new Dimension(width / 5, height);
        int pointPanelHeight = 100;
        Dimension pointPanelSize;
        int rightPanelAmount = pointAmount + 1;

        if ((pointPanelHeight + 6) * rightPanelAmount > height)
            pointPanelSize = new Dimension(width / 5 - 20, height / rightPanelAmount - pointPanels.length * 2);
        else
            pointPanelSize = new Dimension(width / 5 - 20, pointPanelHeight);
        leftPanel.setPreferredSize(controlPanelSize);
        rightPanel.setPreferredSize(controlPanelSize);
        sketch.setPreferredSize(new Dimension(width - 2 * controlPanelSize.width - 20, height));
        xyzPanel.setPreferredSize(pointPanelSize);
        for (PointPanel pointPanel : pointPanels)
            pointPanel.setPreferredSize(pointPanelSize);
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        sketch = null;
        leftPanel = null;
        rightPanel = null;
        xyzPanel = null;
        pointPanels = null;
    }

    @Override
    public String getDescription() {
        return """
                description of exercise 1<br>
                bilinear surface🐺<br>
                most simple, done not on purpose
                """;
    }

    public class PointsPanelDataSetter {
        public void setPointsData(Point3d[] points) {
            for (int pointPanelNumber = 0; pointPanelNumber < pointAmount; pointPanelNumber++) {
                pointPanels[pointPanelNumber].setPoint(points[pointPanelNumber]);
            }
        }

        public Point3d[] getPoints() {
            Point3d[] points = new Point3d[pointAmount];
            for (int pointNumber = 0; pointNumber < pointAmount; pointNumber++) {
                points[pointNumber] = pointPanels[pointNumber].getPoint();
            }
            return points;
        }
    }

    public class SketchPointsDataSetter {
        public void setPointsData(Point3d[] points) {
            sketch.setSurfacePoints(points);
        }

        public void setPause(boolean pause) {
            sketch.pause = pause;
        }
    }
}