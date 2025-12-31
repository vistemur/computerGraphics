package Drawings.graphicElements;

import Drawings.CoordinateSpace.CoordinateSpace;

import java.awt.*;

public interface Drawable {
    public void draw(Graphics g);
    public void setCoordinateSpace(CoordinateSpace coordinateSpace);
    public void countDrawCoordinates();
}
