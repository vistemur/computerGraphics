package windows.Exercises.lab4.Krugaser;

import gui.DrawPanel;

import java.awt.*;

public class Sketch extends DrawPanel {

    Color backgroundColor;
    float r, g, b;
    float rPluser;

    @Override
    protected void setup() {
        r = 0.5f;
        g = 0;
        b = 0;
        rPluser = 0.001f;
        backgroundColor = new Color(r, g, b);
    }

    @Override
    protected void draw() {
        r += rPluser;
        backgroundColor = new Color(r, g, b);
        setBackgroundColor(backgroundColor);
        if (r >= 0.9 || r <= 0.1)
            rPluser *= -1;
    }
}
