package navigation;

import gui.NavigationButton;
import windows.MainMenu;
import windows.Exercises.Excercise1.Exercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NavigationManager {

    private JFrame frame;
    private navigation.Window currentWindow;
    private final navigation.Window[] windows = {new MainMenu(), new Exercise1()};

    public NavigationManager() {
        initFrame();
        addActions();
        setNavigationButtonsManager();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setSize(500, 400);
        frame.setLocation(100, 100);
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void navigateTo(String to) {
        for (navigation.Window window : windows)
            if (window.getName().toLowerCase().contains(to.toLowerCase())) {
                setWindow(window);
                break;
            }
    }

    private void setWindow(Window window) {
        if (currentWindow != null)
            clear();
        currentWindow = window;
        currentWindow.initialize(this);
        frame.add(currentWindow.getPanel());
        updateFrame();
    }

    private void updateFrame() {
        currentWindow.resize(frame.getWidth() , frame.getHeight());
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void clear() {
        frame.remove(currentWindow.getPanel());
        currentWindow.deinitialize();
    }

    public void show() {
        frame.setVisible(true);
    }

    public void addActions() {
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                currentWindow.resize(frame.getWidth() , frame.getHeight());
            }

            public void componentMoved(ComponentEvent event) {
                currentWindow.resize(frame.getWidth(), frame.getHeight());
            }
        });
    }

    private void setNavigationButtonsManager() {
        NavigationButton.setNavigationManager(this);
    }
}
