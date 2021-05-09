package windows.Exercises.lab3.Exercise3_1;

import Drawings.graphicElements.Support.Point3d;
import windows.Exercises.Excercise;
import windows.Exercises.lab3.Exercise3_1.Panels.PointPanel;
import windows.Exercises.lab3.Exercise3_1.Panels.RotationAroundPointPanel;
import windows.Exercises.lab3.Exercise3_1.Panels.XYZPanel;

import javax.swing.*;
import java.awt.*;

public class Exercise3_1 extends Excercise {

    Sketch sketch;
    JPanel leftPanel, rightPanel;
    int pointAmount = 4;
    XYZPanel xyzPanel;
    PointPanel[] pointPanels;
    RotationAroundPointPanel rotationAroundCenterPanel;
    PointsPanelDataSetter pointsPanelDataSetter = new PointsPanelDataSetter();
    SketchPointsDataSetter sketchPointsDataSetter = new SketchPointsDataSetter();
    SketchRotationDataSetter sketchRotationDataSetter = new SketchRotationDataSetter();

    @Override
    protected void initialize() {
        sketch = new Sketch();
        sketch.setDataSource(sketchPointsDataSetter);
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());
        xyzPanel = new XYZPanel("X", "Y", "Z");
        Point3d[] points = sketch.getBuildingPoints();
        pointPanels = new PointPanel[pointAmount];
        for (int pointPanelNumber = 0; pointPanelNumber < pointAmount; pointPanelNumber++) {
            pointPanels[pointPanelNumber] = new PointPanel(pointsPanelDataSetter);
            pointPanels[pointPanelNumber].setPoint(points[pointPanelNumber]);
        }
        rotationAroundCenterPanel = new RotationAroundPointPanel(sketchRotationDataSetter);
        setColoredLayout();
    }

    @Override
    protected void setColoredLayout() {
        super.setColoredLayout();
        leftPanel.setBackground(Color.magenta);
        rightPanel.setBackground(Color.magenta);
        pointPanels[0].setBackground(Color.red);
        pointPanels[1].setBackground(Color.green);
        pointPanels[2].setBackground(Color.blue);
        pointPanels[3].setBackground(Color.black);
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.add(leftPanel, BorderLayout.LINE_START);
        panel.add(sketch, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.LINE_END);
        rightPanel.add(xyzPanel);
        for (var pointPanel : pointPanels)
            rightPanel.add(pointPanel);
        leftPanel.add(rotationAroundCenterPanel);
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
        Dimension rotationPanelSize = new Dimension(controlPanelSize.width - 10, controlPanelSize.height / 3);
        rotationAroundCenterPanel.setPreferredSize(rotationPanelSize);
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        sketch = null;
        leftPanel = null;
        rightPanel = null;
        xyzPanel = null;
        pointPanels = null;
        rotationAroundCenterPanel = null;
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
        public void enterAction(Point3d point) {
            sketch.setSurfacePoints(getPoints());
            sketchRotationDataSetter.enterAction(rotationAroundCenterPanel.getRotation());
        }

        private void enterAction(Point3d[] points) {
            for (int pointPanelNumber = 0; pointPanelNumber < pointAmount; pointPanelNumber++) {
                pointPanels[pointPanelNumber].setPoint(points[pointPanelNumber]);
            }
        }

        private Point3d[] getPoints() {
            Point3d[] points = new Point3d[pointAmount];
            for (int pointNumber = 0; pointNumber < pointAmount; pointNumber++) {
                points[pointNumber] = pointPanels[pointNumber].getPoint();
            }
            return points;
        }

        public void setPause(boolean pause) {
            sketch.pause = pause;
        }
    }

    public class SketchPointsDataSetter {
        public void setPointsData(Point3d[] points) {
            pointsPanelDataSetter.enterAction(points);
        }
    }

    public class SketchRotationDataSetter extends PointsPanelDataSetter {
        public void enterAction(Point3d point) {
            sketch.setRotation(point.x / 100, point.y / 100, point.z / 100);
        }

        public void setPause(boolean pause) {
            sketch.pause = pause;
        }
    }
}