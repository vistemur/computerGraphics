package Drawings.CoordinateSpace;

import Drawings.graphicElements.Drawable;

public interface CoordinateSpace {
    public int[][] convert(int[][] userCoordinates);
    public int convertLengthX(int num);
    public int convertLengthY(int num);
    public int[] getUserData();
    public void setReal(int realMaxX, int realMaxY);
    public Drawable getGrid();
    public void countMouseCoordinates(Mouse mouse);
}
