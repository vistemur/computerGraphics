package windows.Exercises.lab3.Exercise3_1.Panels;

import Drawings.graphicElements.Support.Point3d;
import windows.Exercises.lab3.Exercise3_1.Exercise3_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PointPanel extends JPanel {

    int FieldsAmount = 3;
    int fieldsWidth = 50;
    int fieldsHeight = 30;
    JTextField[] textFields;
    Exercise3_1.PointsPanelDataSetter surfaceDataController;

    public PointPanel(Exercise3_1.PointsPanelDataSetter surfaceDataController) {
        super();
        setLayout(null);
        textFields = new JTextField[FieldsAmount];
        for (int textFieldNumber = 0; textFieldNumber < FieldsAmount; textFieldNumber++) {
            textFields[textFieldNumber] = new JTextField();
            textFields[textFieldNumber].setBounds(0, 0, fieldsWidth, fieldsHeight);
        }
        addActions();
        layoutElements();
        this.surfaceDataController = surfaceDataController;
    }

    private void addActions() {
        for (var textField : textFields) {
            textField.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);
                    PointPanel.this.surfaceDataController.setPause(true);
                }

                public void focusLost(FocusEvent e) {
                    super.focusLost(e);
                    PointPanel.this.surfaceDataController.setPause(false);
                }
            });
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        requestFocusInWindow();
                        PointPanel.this.surfaceDataController.enterAction(PointPanel.this.getPoint());
                    }
                }
            });
        }
    }

    private void layoutElements() {
        for (JTextField textField : textFields)
            add(textField);
    }

    public void setPoint(Point3d point) {
        try {
            textFields[0].setText(String.valueOf((int) point.x));
            textFields[1].setText(String.valueOf((int) point.y));
            textFields[2].setText(String.valueOf((int) point.z));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: unable to set point " + point);
        }
    }

    public Point3d getPoint() {
        return new Point3d( (float) Integer.parseInt(textFields[0].getText()),
                            (float) Integer.parseInt(textFields[1].getText()),
                            (float) Integer.parseInt(textFields[2].getText()));
    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        int blockWidth = dimension.width / FieldsAmount;
        int realFieldWidth = fieldsWidth;
        int realFieldHeight = fieldsHeight;
        if (blockWidth < realFieldWidth)
            realFieldWidth = blockWidth;
        if (dimension.height < realFieldHeight)
            realFieldHeight = dimension.height;
        for (int textFieldNumber = 0; textFieldNumber < FieldsAmount; textFieldNumber++)
            textFields[textFieldNumber].setBounds(
                    (blockWidth - textFields[textFieldNumber].getWidth()) / 2 + blockWidth * textFieldNumber,
                    (dimension.height - textFields[textFieldNumber].getHeight()) / 2,
                    realFieldWidth, realFieldHeight);
    }
}