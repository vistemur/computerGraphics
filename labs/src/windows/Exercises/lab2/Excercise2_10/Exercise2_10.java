package windows.Exercises.lab2.Excercise2_10;

import windows.Exercises.Excercise;
import windows.Exercises.lab2.Excercise2_10.Panels.VerticalValuePanel;

import javax.swing.*;
import java.awt.*;

public class Exercise2_10 extends Excercise {

    VerticalValuePanel kPanel, pointsPanel;
    Sketch sketch;

    int optionPanelsWidth = 100;

    @Override
    protected void initialize() {
        sketch = new Sketch();
        kPanel = new VerticalValuePanel("K", new KChanger(), 1, 6, 3);
        pointsPanel = new VerticalValuePanel("points", new PointsAmountChanger(), 1, 20, 5);
        sketch.setData(5, 3);
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.add(kPanel, BorderLayout.LINE_START);
        panel.add(sketch, BorderLayout.CENTER);
        panel.add(pointsPanel, BorderLayout.LINE_END);
    }

    @Override
    protected void resizeElements(int width, int height) {
        kPanel.setPreferredSize(new Dimension(optionPanelsWidth, height));
        pointsPanel.setPreferredSize(new Dimension(optionPanelsWidth, height));
        sketch.setPreferredSize(new Dimension(width - 2 * optionPanelsWidth - 100, height));
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        sketch = null;
        kPanel = null;
        pointsPanel = null;
    }

    @Override
    public String getDescription() {
        return """
                finally lab2
                """;
    }

    private void setColors() {
        sketch.setBackgroundColor(Color.red);
        kPanel.setBackground(Color.ORANGE);
        pointsPanel.setBackground(Color.green);
    }

    private class KChanger implements ValueChangeable {
        public void set(int value) {
            sketch.setK(value);
        }
    }

    private class PointsAmountChanger implements ValueChangeable {
        public void set(int value) {
            sketch.setPointsAmount(value);
        }
    }
}
