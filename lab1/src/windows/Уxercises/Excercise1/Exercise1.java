package windows.Уxercises.Excercise1;

import gui.DrawPanel;
import windows.Уxercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise1 extends Excercise {

    DrawPanel sketch;

    @Override
    protected void initialize() {
        sketch = new Sketch1();
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
