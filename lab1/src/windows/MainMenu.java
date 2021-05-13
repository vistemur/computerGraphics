package windows;

import gui.NavigationButton;
import windows.Exercises.Excercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends navigation.Window {

    private JButton nextButton;
    private JButton previousButton;
    private JLabel labNumberLabel;
    private JPanel topPanel;
    private JPanel buttonsPanel;
    private JPanel infoPanel;
    private JLabel descriptionLabel;
    private NavigationButton[] buttons;

    private static int menuNumber = 0; // labNumber - 1
    private final String[][][] labStrings = {   {{"Exercise1_01", "1"}, {"Exercise1_02", "2"}, {"Exercise1_10", "10"}, {"ABCDMatrixTest", "abcd"}},
                                                {{"Exercise2_10", "10"}, {"ExerciseSpline", "simple spline"}},
                                                {{"CubeExercise", "cube"}, {"SurfaceExercise", "surface"}, {"Exercise3_1", "1"}},
                                                {{"QuadLines", "rectangle and lines"}}};

    @Override
    protected void init() {
        topPanel = new JPanel();
        labNumberLabel = new JLabel();
        setLabNumberLabelText();
        nextButton = new JButton("next");
        nextButton.setPreferredSize(new Dimension(80, 80));
        previousButton = new JButton("previous");
        previousButton.setPreferredSize(new Dimension(80, 80));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(labStrings[menuNumber].length, 1));

        infoPanel = new JPanel();
        descriptionLabel = new JLabel();

        setMenu(menuNumber);
        addTopButtonsActions();
        setBackgroundColors();
    }

    private void addTopButtonsActions() {
        nextButton.addActionListener(e -> { MainMenu.this.nextLab(); });
        previousButton.addActionListener(e -> { MainMenu.this.previousLab(); });
    }

    private void nextLab() {
        setMenu(menuNumber + 1);
    }

    private void previousLab() {
        setMenu(menuNumber - 1);
    }

    public void setMenu(int menuNumber) {
        if (menuNumber >= labStrings.length || menuNumber < 0)
            return;
        MainMenu.menuNumber = menuNumber;
        if (menuNumber == 0)
            previousButton.setVisible(false);
        if (menuNumber == labStrings.length - 1)
            nextButton.setVisible(false);
        setLabNumberLabelText();
        rebuildMenu();
    }

    private void rebuildMenu() {
        if (menuNumber < labStrings.length - 1)
            nextButton.setVisible(true);
        if (menuNumber > 0)
            previousButton.setVisible(true);
        setupButtons();
        addButtons();
    }

    private void setLabNumberLabelText() {
        labNumberLabel.setText("lab " + (menuNumber + 1));
    }

    private void setupButtons() {
        int buttonsAmount;
        String windowName;

        if (menuNumber >= labStrings.length || menuNumber < 0)
            return;

        buttonsAmount = labStrings[menuNumber].length;
        buttons = new NavigationButton[buttonsAmount];
        for (int counter = 0; counter < buttonsAmount; counter++) {
            windowName = labStrings[menuNumber][counter][0];
            buttons[counter] = new NavigationButton(windowName);
            String description = "";
            try {
                description = ((Excercise) navigationManager.getWindow(windowName)).getDescription();
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
            buttons[counter].setText(labStrings[menuNumber][counter][1]);
        }
    }

    private void setBackgroundColors() {
        topPanel.setBackground(Color.ORANGE);
        buttonsPanel.setBackground(Color.CYAN);
        infoPanel.setBackground(Color.BLUE);
        descriptionLabel.setBackground(Color.GREEN);
    }

    @Override
    protected void layout(JPanel panel) {
        topPanel.add(previousButton, BorderLayout.LINE_START);
        topPanel.add(labNumberLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.LINE_END);
        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(buttonsPanel, BorderLayout.LINE_START);
        infoPanel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.LINE_END);
        panel.setBackground(Color.RED);
    }

    private void addButtons() {
        try {
            buttonsPanel.removeAll();
            buttonsPanel.setLayout(new GridLayout(labStrings[menuNumber].length, 1));
            for (JButton button : buttons)
                buttonsPanel.add(button);
        } catch (Exception e) {
            System.out.print("ERROR: unable to add buttons");
            e.printStackTrace();
        }
    }

    @Override
    public void resize(int width, int height) {
        topPanel.setPreferredSize(new Dimension(width - 15, 100));
        buttonsPanel.setPreferredSize(new Dimension(200, height - 140));
        infoPanel.setPreferredSize(new Dimension(width - 220, height - 140));
    }

    @Override
    public void deinitialize() {
        super.deinitialize();
        nextButton = null;
        previousButton = null;
        labNumberLabel = null;
        topPanel = null;
        buttonsPanel = null;
        infoPanel = null;
        descriptionLabel = null;
        buttons = null;
    }
}
