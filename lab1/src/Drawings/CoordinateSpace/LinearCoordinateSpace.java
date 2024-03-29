package Drawings.CoordinateSpace;

import Drawings.graphicElements.Drawable;

import java.awt.*;

public class LinearCoordinateSpace implements CoordinateSpace {

    float userMinX, userMaxX;
    float userMinY, userMaxY;
    int realMaxY, realMaxX;
    int realMinY, realMinX;

    public LinearCoordinateSpace(float userMinX, float userMaxX, float userMinY, float userMaxY) {
        this.userMinX = userMinX;
        this.userMaxX = userMaxX;
        this.userMinY = userMinY;
        this.userMaxY = userMaxY;
        realMinX = 0;
        realMinY = 0;
    }

    public void setReal(int realMaxX, int realMaxY) {
        this.realMaxX = realMaxX;
        this.realMaxY = realMaxY;
    }

    public float[] getUserData() {
        return new float[] {userMinX, userMaxX, userMinY, userMaxY};
    }

    @Override
    public int[][] convert(float[][] userCoordinates) {
        int[][] realCoordinates;
        int pointsAmount;

        pointsAmount = userCoordinates[0].length;
        realCoordinates = new int[2][pointsAmount];
        for (int c = 0; c < pointsAmount; c++) {
            realCoordinates[0][c] = convert(userCoordinates[0][c], userMinX, userMaxX, realMinX, realMaxX);
            realCoordinates[1][c] = convert(userCoordinates[1][c], userMinY, userMaxY, realMinY, realMaxY);
        }
        return realCoordinates;
    }

    private int convert(float num, float userMin, float userMax, int realMin,  int realMax) {
        return (int) ((num - userMin) * (realMax - realMin) / (userMax - userMin) + realMin);
    }

    public int convertLengthX(float num) {
        float max, min;

       if (userMaxX > userMinX) {
           max = userMaxX;
           min = userMinX;
       } else {
           max = userMinX;
           min = userMaxX;
       }
        return (int) (num * (realMaxX - realMinX) / (max - min));
    }

    public int convertLengthY(float num) {
        float max, min;

        if (userMaxY > userMinY) {
            max = userMaxY;
            min = userMinY;
        } else {
            max = userMinY;
            min = userMaxY;
        }
        return (int) (num * (realMaxY - realMinY) / (max - min));
    }

    public Drawable getGrid() {
        return new Drawable() {

            int[][] drawPoints = new int[2][4];
            float[][] points;
            CoordinateSpace coordinateSpace;

            @Override
            public void draw(Graphics g) {
                g.setColor(Color.blue);
                g.drawLine(drawPoints[0][0], drawPoints[1][0], drawPoints[0][1], drawPoints[1][1]);
                g.drawLine(drawPoints[0][2], drawPoints[1][2], drawPoints[0][3], drawPoints[1][3]);
                drawNumbers(g, userMinX, userMaxX, 5, false);
                drawNumbers(g, userMinY, userMaxY, 5, true);
            }

            private void drawNumbers(Graphics g, float from, float to, int amount, boolean osY) {
                int x, y;
                String str;

                x = 0;
                y = 0;
                if (from > to) {
                    float temp = to;
                    to = from;
                    from = temp;
                }
                if (osY)
                    x = drawPoints[0][2];
                else
                    y = drawPoints[1][0];
                for (float counter = from; counter <= to; counter += (to - from) / amount) {
                    str = String.valueOf(counter);
                    if (osY) {
                        y = convert(counter, userMinY, userMaxY, realMinY, realMaxY);
                        g.drawLine(x - 10, y, x + 10, y);
                        g.drawString(str, x + 5, y + ((y > (realMaxY / 2)) ? -5 : 15));
                    } else {
                        x = convert(counter, userMinX, userMaxX, realMinX, realMaxX);
                        g.drawLine(x, y - 10, x, y + 10);
                        g.drawString(str, x - ((x > (realMaxX / 2)) ? str.length() * 10 : 0), y - 10);
                    }
                }
            }

            @Override
            public void setCoordinateSpace(CoordinateSpace coordinateSpace) {
                this.coordinateSpace = coordinateSpace;
                points = new float[][] {  {userMinX, userMaxX, (userMaxX + userMinX) / 2, (userMaxX + userMinX) / 2},
                                        {(userMaxY + userMinY) / 2, (userMaxY + userMinY) / 2, userMinY, userMaxY}};
            }

            @Override
            public void countDrawCoordinates() {
                drawPoints = coordinateSpace.convert(points);
            }
        };
    }

    public void countMouseCoordinates(Mouse mouse) {
        mouse.x = deConvert(mouse.x, userMinX, userMaxX, realMinX, realMaxX);
        mouse.y = deConvert(mouse.y, userMinY, userMaxY, realMinY, realMaxY);
    }

    private float deConvert(float num, float userMin, float userMax, int realMin,  int realMax) {
        if (realMax == 0)
            return 0;
        return ((num - realMin) * (userMax - userMin) / (realMax - realMin) + userMin);
    }
}