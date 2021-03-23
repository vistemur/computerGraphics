package windows.Exercises.Exercise3;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise3 extends Excercise {
    DrawPanel sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch3();
    }

    @Override
    protected void layoutElements(JPanel panel) {
        panel.add(sketch);
    }

    @Override
    protected void resizeElements(int width, int height) {
        sketch.setPreferredSize(new Dimension(width, height));
    }
}
