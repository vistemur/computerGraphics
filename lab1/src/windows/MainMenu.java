package windows;
import gui.NavigationButton;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends navigation.Window {

    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private final String[] buttonNames = {"Exercise1_01", "Exercise1_02", "Exercise1_10", "abcd"};
    private NavigationButton[] buttons;

    @Override
    protected void init() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(buttonNames.length, 1));

        infoPanel = new JPanel();

        buttons = new NavigationButton[buttonNames.length];
        for (int c = 0; c < buttonNames.length; c++) {
            buttons[c] = new NavigationButton(buttonNames[c]);
        }
        setButtonsText();
        setBackgroundColors();
    }

    private void setButtonsText() {
        buttons[0].setText("1.1");
        buttons[1].setText("1.2");
        buttons[2].setText("1.10");
        buttons[3].setText("ABCD");
    }

    private void setBackgroundColors() {
        buttonsPanel.setBackground(Color.CYAN);
        infoPanel.setBackground(Color.BLUE);
    }

    @Override
    protected void layout(JPanel panel) {
        for (JButton button : buttons)
            buttonsPanel.add(button);
        panel.add(buttonsPanel, BorderLayout.LINE_START);
        panel.add(infoPanel, BorderLayout.LINE_END);

        panel.setBackground(Color.RED);
    }

    @Override
    public void resize(int width, int height) {
        buttonsPanel.setPreferredSize(new Dimension(200, height - 40));
        infoPanel.setPreferredSize(new Dimension(width - 220, height - 40));
    }
}
