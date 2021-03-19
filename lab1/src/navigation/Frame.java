package navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {

    public Frame() {
        super();
        setSize(500, 400);
        setLocation(100, 100);
        setMinimumSize(new Dimension(100, 100));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setKeyListener();
    }

    private void setKeyListener() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 70) {
            toggleFullScreen();
        }
    }

    private void toggleFullScreen() {
        if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            setExtendedState(JFrame.NORMAL);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
}
