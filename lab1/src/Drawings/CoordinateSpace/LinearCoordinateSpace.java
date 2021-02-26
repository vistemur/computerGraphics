package Drawings.CoordinateSpace;

import Matrix.Matrix;

public class LinearCoordinateSpace implements CoordinateSpace {

    int userMinX, userMaxX;
    int userMinY, userMaxY;
    int realMaxY, realMaxX;

    public LinearCoordinateSpace(int userMinX, int userMaxX, int userMinY, int userMaxY) {
        this.userMinX = userMinX;
        this.userMaxX = userMaxX;
        this.userMinY = userMinY;
        this.userMaxY = userMaxY;
    }

    public void setReal(int realMaxX, int realMaxY) {
        this.realMaxX = realMaxX;
        this.realMaxY = realMaxY;
    }

    @Override
    public int[][] convert(int[][] userCoordinates) {
        int[][] realCoordinates;
        int pointsAmount;

        pointsAmount = userCoordinates[0].length;
        realCoordinates = new int[2][pointsAmount];
        for (int c = 0; c < pointsAmount; c++) {
            realCoordinates[0][c] = convert(userCoordinates[0][c], userMinX, userMaxX, realMaxX);
            realCoordinates[1][c] = convert(userCoordinates[1][c], userMinY, userMaxY, realMaxY);
        }
        Matrix.print(userCoordinates);
        System.out.println();
        Matrix.print(realCoordinates);
        return realCoordinates;
    }

    private int convert(int num, int userMin, int userMax, int realMax) {
        int answer;

        answer = 0;
        System.out.println("userMax = " + userMax);
        System.out.println("realMax = " + realMax);
        //TODO: вывести нормальную формулу
        answer = num * realMax / userMax;
        return answer;
    }
}
