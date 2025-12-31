package windows.Exercises.lab6.Excercise6_4;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise6_4 extends Excercise {

    Sketch sketch;

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
                super cool stuff blin🐺
                """;
    }
}