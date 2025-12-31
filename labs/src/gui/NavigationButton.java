package gui;

import navigation.NavigationManager;

import javax.swing.*;

public class NavigationButton extends JButton {
    private static NavigationManager navigationManager;
    private String navigationLocation;

    public NavigationButton(String to) {
        navigationLocation = to;
        this.addActionListener(e -> navigationManager.navigateTo(navigationLocation));
    }

    public void setNavigationLocation(String to) {
        navigationLocation = to;
    }

    public static void setNavigationManager(NavigationManager navigationManager) {
        NavigationButton.navigationManager = navigationManager;
    }
}
