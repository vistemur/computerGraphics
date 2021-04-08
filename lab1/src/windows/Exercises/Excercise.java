package windows.Exercises;

import gui.NavigationButton;
import navigation.Window;

import javax.swing.*;
import java.awt.*;

public abstract class Excercise extends Window {

    private JPanel header;
    private JPanel spacer;
    private NavigationButton backButton;
    private JPanel content;

    @Override
    protected void init() {
        header = new JPanel();
        header.setLayout(new FlowLayout());
        header.setBackground(Color.green);

        spacer = new JPanel();
        header.setBackground(Color.green);

        backButton = new NavigationButton("MainMenu");
        backButton.setPreferredSize(new Dimension(80, 80));

        content = new JPanel();
        content.setBackground(Color.MAGENTA);
        initialize();
    }

    @Override
    protected void layout(JPanel panel) {
        header.add(backButton);
        header.add(spacer);
        panel.add(header, BorderLayout.PAGE_START);
        layoutElements(content);
        panel.add(content, BorderLayout.CENTER);
        panel.setBackground(Color.BLACK);
    }

    @Override
    public void resize(int width, int height) {
        header.setPreferredSize(new Dimension(width, 100));
        spacer.setPreferredSize(new Dimension(width - 100, 80));
        content.setPreferredSize(new Dimension(width, height - 100));
        resizeElements(width, height - 140);
    }

    public String getDescription() {
       return "description";
    }

    protected abstract void initialize();
    protected abstract void layoutElements(JPanel panel);
    protected abstract void resizeElements(int width, int height);
}
