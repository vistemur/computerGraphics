package navigation;

import gui.NavigationButton;
import windows.Exercises.lab1.ABCDMatrixTest.ABCDMatrixTest;
import windows.Exercises.lab1.Exercise1_01.Exercise1_01;
import windows.Exercises.lab1.Exercise1_02.Exercise1_02;
import windows.MainMenu;
import windows.Exercises.lab1.Exercise1_10.Exercise1_10;

import javax.swing.*;
import java.awt.event.*;

public class NavigationManager {

    private Frame frame;
    private navigation.Window currentWindow;
    private navigation.Window[] windows = {   new MainMenu(), new Exercise1_10(), new Exercise1_01(), new Exercise1_02(),
                                                    new ABCDMatrixTest()};

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
