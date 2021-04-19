package windows.Exercises.lab2.Excercise2_10;

import gui.DrawPanel;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;

public class Exercise2_10 extends Excercise {

    //JPanel optionsPanel;
    DrawPanel sketch;

    //int optionPanelHeight = 100;

    @Override
    protected void initialize() {
        //optionsPanel = new JPanel();
        sketch = new Sketch();
    }

    @Override
    protected void layoutElements(JPanel panel) {
       // panel.add(optionsPanel, BorderLayout.PAGE_START);
        panel.add(sketch, BorderLayout.CENTER);
    }

    @Override
    protected void resizeElements(int width, int height) {
//        optionsPanel.setPreferredSize(new Dimension(width, optionPanelHeight));
//        sketch.setPreferredSize(new Dimension(width, height - optionPanelHeight - 10));
        sketch.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        //optionsPanel = null;
        sketch = null;
    }

    @Override
    public String getDescription() {
        return """
                finally lab2
                """;
    }
}
