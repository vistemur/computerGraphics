import navigation.NavigationManager;
import windows.Exercises.lab1.ABCDMatrixTest.ABCDMatrixTest;
import windows.Exercises.lab1.Exercise1_01.Exercise1_01;
import windows.Exercises.lab1.Exercise1_10.Exercise1_10;
import windows.Exercises.lab1.Exercise1_02.Exercise1_02;
import windows.Exercises.lab2.Excercise2_10.Exercise2_10;
import windows.Exercises.lab2.Spline.ExerciseSpline;
import windows.Exercises.lab3.Cube.CubeExercise;
import windows.Exercises.lab3.Exercise3_1.Exercise3_1;
import windows.Exercises.lab3.Surface.SurfaceExercise;
import windows.Exercises.lab4.Krugaser.Krugaser;
import windows.Exercises.lab4.QuadLines.QuadLines;
import windows.Exercises.lab5.Excercise5_3.Exercise5_3;
import windows.Exercises.lab5.Excercise5_8.Exercise5_8;
import windows.Exercises.lab6.Excercise6_4.Exercise6_4;
import windows.MainMenu;

public class App {

    private final NavigationManager navigationManager;

    public App() {
        navigationManager = new NavigationManager(
                new MainMenu(), new Exercise1_10(), new Exercise1_01(),
                new Exercise1_02(), new ABCDMatrixTest(), new Exercise2_10(),
                new ExerciseSpline(), new CubeExercise(), new SurfaceExercise(),
                new Exercise3_1(), new QuadLines(), new Krugaser(),
                new Exercise5_3(), new Exercise5_8(), new Exercise6_4());
    }

    public void show() {
//        navigationManager.navigateTo("MainMenu");
        navigationManager.navigateTo("Exercise6_4");
        navigationManager.show();
    }
}
