package Drawings.CoordinateSpace;

import Drawings.graphicElements.Drawable;

import java.awt.*;

public interface CoordinateSpace {
    public int[][] convert(int[][] userCoordinates);
    public int convertX(int num);
    public int convertY(int num);
    public void setReal(int realMaxX, int realMaxY);
    public Drawable getGrid();
}
