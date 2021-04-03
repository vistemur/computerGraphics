package windows.Exercises.lab1.ABCDMatrixTest;

import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends JPanel {

    JPanel topPanel, lowPanel;
    JTextField[] textFields;

    public MatrixPanel() {
        super();
        topPanel = new JPanel();
        lowPanel = new JPanel();
        textFields = new JTextField[4];
        for (int c = 0; c < textFields.length; c++) {
            textFields[c] = new JTextField();
            textFields[c].setPreferredSize(new Dimension(50, 30));
            if (c == 0 || c == 3)
                textFields[c].setText("1");
            else
                textFields[c].setText("0");
            textFields[c].setHorizontalAlignment(JTextField.CENTER);
        }
        setPanels();
        layoutElements();
    }

    private void setPanels() {
        topPanel.setPreferredSize(new Dimension(300, 40));
        lowPanel.setPreferredSize(new Dimension(300, 40));
        topPanel.setLayout(new FlowLayout());
        lowPanel.setLayout(new FlowLayout());
    }

    private void layoutElements() {
        topPanel.add(new JLabel("A"));
        topPanel.add(textFields[0]);
        topPanel.add(textFields[1]);
        topPanel.add(new JLabel("B"));
        lowPanel.add(new JLabel("C"));
        lowPanel.add(textFields[2]);
        lowPanel.add(textFields[3]);
        lowPanel.add(new JLabel("D"));
        add(topPanel, BorderLayout.PAGE_START);
        add(lowPanel, BorderLayout.CENTER);
    }

    public double[][] getMatrix() {
        double[][] matrix;

        matrix = new double[2][2];
        matrix[0][0] = Double.parseDouble(textFields[0].getText());
        matrix[0][1] = Double.parseDouble(textFields[1].getText());
        matrix[1][0] = Double.parseDouble(textFields[2].getText());
        matrix[1][1] = Double.parseDouble(textFields[3].getText());
        return matrix;
    }

    public void setColors() {
        setBackground(Color.MAGENTA);
        topPanel.setBackground(Color.MAGENTA);
        lowPanel.setBackground(Color.MAGENTA);
    }
}
