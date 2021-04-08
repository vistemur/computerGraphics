package windows;
import gui.NavigationButton;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends navigation.Window {

    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private JLabel descriptionLabel;
    private final String[] buttonNames = {"Exercise1_01", "Exercise1_02", "Exercise1_10", "abcd"};
    private NavigationButton[] buttons;

    @Override
    protected void init() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(buttonNames.length, 1));

        infoPanel = new JPanel();
        descriptionLabel = new JLabel();

        buttons = new NavigationButton[buttonNames.length];
        for (int counter = 0; counter < buttonNames.length; counter++) {
            buttons[counter] = new NavigationButton(buttonNames[counter]);
            String description = buttonNames[counter];
            try {
                description = ((Excercise) navigationManager.getWindow(buttonNames[counter])).getDescription();
            } catch (Exception e) {
                System.out.print("ERROR " + e.getMessage());
            }
            String finalDescription = "<html>" + description + "</html>";
            buttons[counter].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    MainMenu.this.descriptionLabel.setText(finalDescription);
                }
            });
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
        descriptionLabel.setBackground(Color.GREEN);
    }

    @Override
    protected void layout(JPanel panel) {
        for (JButton button : buttons)
            buttonsPanel.add(button);
        panel.add(buttonsPanel, BorderLayout.LINE_START);
        infoPanel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.LINE_END);

        panel.setBackground(Color.RED);
    }

    @Override
    public void resize(int width, int height) {
        buttonsPanel.setPreferredSize(new Dimension(200, height - 40));
        infoPanel.setPreferredSize(new Dimension(width - 220, height - 40));
    }
}
