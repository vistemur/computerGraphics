import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Drawing extends JPanel {

    Timer timer;
    int a = 0;

    Drawing() {
        Drawing drawing = this;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawing.repaint();
            }
        }, 0, 1000);
    }

    public void paintComponent(Graphics g) {
        g.fillOval(a, 100, 200, 200);
        g.drawLine(0, 0, 500, 500);
        if (a < 500)
            a += 10;
    }
}
