package windows.Exercises.lab3.Surface;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class SurfaceExercise extends Excercise {

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
                supeer dupper fast garazd
                """;
    }
}
