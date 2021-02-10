import javax.swing.*;

public class App {

    JFrame frame;
    Drawing drawing;

    App() {
        frame = new JFrame("app");
        frame.setSize(500, 500);

        drawing = new Drawing();
        drawing.setSize(500, 500);

        addListeners();
        layout();
    }

    private void layout() {
        frame.add(drawing);
    }

    private void addListeners() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        frame.setVisible(true);
    }
}
