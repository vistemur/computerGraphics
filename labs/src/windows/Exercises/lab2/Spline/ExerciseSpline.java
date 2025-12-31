package windows.Exercises.lab2.Spline;

import gui.DrawPanel;
import windows.Exercises.Excercise;
import windows.Exercises.lab2.Spline.Sketch;

import javax.swing.*;
import java.awt.*;

public class ExerciseSpline extends Excercise {

    DrawPanel sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch();
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.add(sketch);
    }

    @Override
    protected void resizeElements(int width, int height) {
        sketch.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        sketch = null;
    }

    @Override
    public String getDescription() {
        return """
                spline for training
                """;
    }
}
