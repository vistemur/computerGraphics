package windows.Exercises.lab4.QuadLines;

import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class QuadLines extends Excercise {
    Sketch sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch();
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(sketch);
    }

    @Override
    protected void resizeElements(int width, int height) {
        sketch.setBounds(0, 0, width, height);
        sketch.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public String getDescription() {
        return """
                just rectangle and lines <br>
                click mouse to randomize lines locations
                """;
    }
}
