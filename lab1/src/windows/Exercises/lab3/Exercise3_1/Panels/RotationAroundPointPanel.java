package windows.Exercises.lab3.Exercise3_1.Panels;

import Drawings.graphicElements.Support.Point3d;
import windows.Exercises.lab3.Exercise3_1.Exercise3_1;

import javax.swing.*;
import java.awt.*;

public class RotationAroundPointPanel extends JPanel {

    JLabel pointLabelPanel;
    XYZPanel xyzPanel1;
    PointPanel rotationValuesPanel;
    Point3d defaultRotation;

    public RotationAroundPointPanel(Exercise3_1.SketchRotationDataSetter surfaceDataController) {
        defaultRotation = new Point3d(1, 1, 0);
        setLayout(new FlowLayout());
        xyzPanel1 = new XYZPanel("X", "Y", "Z");
        pointLabelPanel = new JLabel("rotation around center");
        rotationValuesPanel = new PointPanel(surfaceDataController);
        rotationValuesPanel.setPoint(defaultRotation);
        surfaceDataController.enterAction(defaultRotation);
        layoutElements();
    }

    public Point3d getRotation() {
        return rotationValuesPanel.getPoint();
    }

    private void layoutElements() {
        add(pointLabelPanel);
        add(xyzPanel1);
        add(rotationValuesPanel);
    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        Dimension blockDimension = new Dimension(dimension.width - 10, dimension.height / 3 - 5);
        xyzPanel1.setPreferredSize(blockDimension);
        pointLabelPanel.setPreferredSize(blockDimension);
        rotationValuesPanel.setPreferredSize(blockDimension);
    }
}
