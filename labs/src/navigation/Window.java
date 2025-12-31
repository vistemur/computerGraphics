package navigation;

import javax.swing.*;

public abstract class Window {
    private JPanel panel;
    protected NavigationManager navigationManager;

    public void initialize(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
        panel = new JPanel();
        init();
        layout(panel);
    }

    public void deinitialize() {
        panel = null;
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getName() {
        return getClass().getName();
    }

    protected abstract void init();
    protected abstract void layout(JPanel panel);
    public void resize(int width, int height) {}
}
