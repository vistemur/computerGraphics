package windows.Exercises.lab3.Exercise3_1.Panels;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ParamPanel extends JPanel {

    JButton plusButton, minusButton;

    public ParamPanel() {
        super();
        plusButton = new JButton();
        minusButton = new JButton();

        URL iconUrl = this.getClass().getResource("/res/img/plus.png");
        Toolkit tk = this.getToolkit();
        plusButton.setIcon((Icon) tk.getImage("/res/img/plus.png"));

        layoutElements();
    }

    private void layoutElements() {
        add(plusButton);
    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
    }
}
