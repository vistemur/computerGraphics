package Drawings.CoordinateSpace;

import Drawings.graphicElements.Drawable;

public class ConstantCoordinateSpace  implements CoordinateSpace{
    @Override
    public int[][] convert(int[][] userCoordinates) {
        return new int[0][];
    }

    @Override
    public int convertLengthX(int num) {
        return 0;
    }

    @Override
    public int convertLengthY(int num) {
        return 0;
    }

    @Override
    public void setReal(int realMaxX, int realMaxY) {

    }

    @Override
    public Drawable getGrid() {
        return null;
    }

    @Override
    public void countMouseCoordinates(Mouse mouse) {

    }
}
