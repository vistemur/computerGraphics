package Drawings.CoordinateSpace;

import Drawings.graphicElements.Support.Point;

public class Mouse {
    public float x, y;

    public Point getPoint() {
        return new Point(x, y);
    }
}
