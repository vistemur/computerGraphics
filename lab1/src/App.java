import navigation.NavigationManager;
import windows.Exercises.lab1.ABCDMatrixTest.ABCDMatrixTest;
import windows.Exercises.lab1.Exercise1_01.Exercise1_01;
import windows.Exercises.lab1.Exercise1_10.Exercise1_10;
import windows.Exercises.lab1.Exercise1_02.Exercise1_02;
import windows.Exercises.lab2.Excercise2_10.Exercise2_10;
import windows.MainMenu;

public class App {

    private final NavigationManager navigationManager;

    public App() {
        navigationManager = new NavigationManager(
                new MainMenu(), new Exercise1_10(), new Exercise1_01(),
                new Exercise1_02(), new ABCDMatrixTest(), new Exercise2_10());
    }

    public void show() {
        navigationManager.navigateTo("MainMenu");
        navigationManager.show();
    }
}
