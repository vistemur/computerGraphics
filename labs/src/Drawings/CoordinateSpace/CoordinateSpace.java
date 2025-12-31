package Drawings.CoordinateSpace;

import Drawings.graphicElements.Drawable;

public interface CoordinateSpace {
    public int[][] convert(float[][] userCoordinates);
    public int convertLengthX(float num);
    public int convertLengthY(float num);
    public float[] getUserData();
    public void setReal(int realMaxX, int realMaxY);
    public Drawable getGrid();
    public void countMouseCoordinates(Mouse mouse);
}
