package windows.Exercises.lab1.Exercise1_10;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise1_10 extends Excercise {

    DrawPanel sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch();
        sketch.setBackground(Color.MAGENTA);
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
