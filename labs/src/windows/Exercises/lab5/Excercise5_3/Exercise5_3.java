package windows.Exercises.lab5.Excercise5_3;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise5_3  extends Excercise {

    DrawPanel sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch();
        setColoredLayout();
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
                just a cube...again
                """;
    }
}
