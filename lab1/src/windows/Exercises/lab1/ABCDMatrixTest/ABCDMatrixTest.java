package windows.Exercises.lab1.ABCDMatrixTest;

import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class ABCDMatrixTest extends Excercise {

    ABCDMatrixSketch sketch;
    JPanel optionsPanel;
    JButton applyMatrixButton;
    JButton resetButton;
    MatrixPanel matrixPanel;

    int optionPanelHeight = 100;

    @Override
    protected void initialize() {
        sketch = new ABCDMatrixSketch();
        optionsPanel = new JPanel();
        initOptionButton(resetButton = new JButton(), "reset");
        initOptionButton(applyMatrixButton = new JButton(), "apply");
        initMatrixPanel();
        setActions();
        setColors();
    }

    private void initMatrixPanel() {
        matrixPanel = new MatrixPanel();
        matrixPanel.setPreferredSize(new Dimension(300, optionPanelHeight - 10));
    }

    private void setActions() {
        applyMatrixButton.addActionListener(e -> sketch.applyMatrix(matrixPanel.getMatrix()));
        resetButton.addActionListener(e -> sketch.reset());
    }

    private void setColors() {
        optionsPanel.setBackground(Color.MAGENTA);
        matrixPanel.setColors();
    }

    private void initOptionButton(JButton optionButton, String text) {
        optionButton.setText(text);
        optionButton.setPreferredSize(new Dimension(100, optionPanelHeight - 10));
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.setLayout(new FlowLayout());
        optionsPanel.add(resetButton);
        optionsPanel.add(matrixPanel);
        optionsPanel.add(applyMatrixButton);
        panel.add(optionsPanel, BorderLayout.PAGE_START);
        panel.add(sketch, BorderLayout.CENTER);
    }

    @Override
    protected void resizeElements(int width, int height) {
        optionsPanel.setPreferredSize(new Dimension(width, optionPanelHeight));
        sketch.setPreferredSize(new Dimension(width, height - optionPanelHeight - 10));
    }
}
