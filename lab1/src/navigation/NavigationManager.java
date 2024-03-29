package navigation;

import gui.NavigationButton;

import javax.swing.*;
import java.awt.event.*;

public class NavigationManager {

    private Frame frame;
    private navigation.Window currentWindow;
    private navigation.Window[] windows;

    public NavigationManager(Window ... windows) {
        this.windows = windows;
        initFrame();
        addActions();
        setNavigationButtonsManager();
    }

    private void initFrame() {
        frame = new Frame();
    }

    public void navigateTo(String to) {
        try {
            setWindow(getWindow(to));
        } catch (Exception e) {
            System.out.print("ERROR " + e.getMessage());
        }
    }

    public Window getWindow(String to) throws Exception {
        for (navigation.Window window : windows)
            if (window.getName().toLowerCase().contains(to.toLowerCase())) {
                return window;
            }
        throw new Exception("unable to find window named " + to);
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
        changeSize();
        SwingUtilities.updateComponentTreeUI(frame);
    }

    private void clear() {
        frame.remove(currentWindow.getPanel());
        currentWindow.deinitialize();
    }

    private void changeSize() {
        currentWindow.resize(frame.getWidth(), frame.getHeight());
    }

    public void show() {
        frame.setVisible(true);
    }

    public void addActions() {
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent event) {
                changeSize();
            }

            public void componentMoved(ComponentEvent event) {
                changeSize();
            }
        });
    }

    private void setNavigationButtonsManager() {
        NavigationButton.setNavigationManager(this);
    }
}
