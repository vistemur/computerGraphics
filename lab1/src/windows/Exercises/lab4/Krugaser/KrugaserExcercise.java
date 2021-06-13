package windows.Exercises.lab4.Krugaser;

import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class KrugaserExcercise extends Excercise {

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
}
