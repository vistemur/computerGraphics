package windows;
import gui.NavigationButton;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends navigation.Window {

    private JPanel buttonsPanel;
    private final String[] buttonNames = {"1", "2"};
    private NavigationButton[] buttons;

    @Override
    protected void init() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(buttonNames.length, 1));
        buttonsPanel.setBackground(Color.CYAN);

        buttons = new NavigationButton[buttonNames.length];
        for (int c = 0; c < buttonNames.length; c++) {
            buttons[c] = new NavigationButton(buttonNames[c]);
            buttons[c].setPreferredSize(new Dimension(100, 100));
        }
    }

    @Override
    protected void layout(JPanel panel) {
        for (JButton button : buttons)
            buttonsPanel.add(button);
        panel.add(buttonsPanel);
        panel.setBackground(Color.RED);
    }

    @Override
    public void resize(int width, int height) {
        buttonsPanel.setPreferredSize(new Dimension(width / 3, height - 50));
    }
}
