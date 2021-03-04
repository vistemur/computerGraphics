import navigation.NavigationManager;

public class App {

    private NavigationManager navigationManager;

    public App() {
        navigationManager = new NavigationManager();
    }

    public void show() {
//        navigationManager.navigateTo("MainMenu");
        navigationManager.navigateTo("Exercise1");
        navigationManager.show();
    }
}
