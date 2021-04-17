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

        spacer = new JPanel();

        backButton = new NavigationButton("MainMenu");
        backButton.setPreferredSize(new Dimension(80, 80));

        content = new JPanel();
        initialize();
    }

    @Override
    protected void layout(JPanel panel) {
        header.add(backButton);
        header.add(spacer);
        panel.add(header, BorderLayout.PAGE_START);
        layoutElements(content);
        panel.add(content, BorderLayout.CENTER);
    }

    protected void setColoredLayout() {
        header.setBackground(Color.green);
        header.setBackground(Color.green);
        content.setBackground(Color.MAGENTA);
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
