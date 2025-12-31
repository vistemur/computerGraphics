package Drawings.graphicElements.Dimension3d.Visibility;

import Drawings.graphicElements.Dimension3d.Triangle3d;

public class ZSorter extends VisibilityController {
    public void SetVisible(Triangle3d[] triangle3ds) {
        for (int frontCounter = 1; frontCounter < triangle3ds.length; frontCounter++)
            if (triangle3ds[frontCounter].getCenter().z > triangle3ds[frontCounter - 1].getCenter().z)
                replaceToFront(triangle3ds, frontCounter);
    }

    private void replaceToFront(Triangle3d[] triangle3ds, int from) {
        replaceTriangles(triangle3ds, from - 1, from);
        for (int backCounter = from - 1; backCounter >= 1; backCounter--)
            if (triangle3ds[backCounter].getCenter().z > triangle3ds[backCounter - 1].getCenter().z)
                replaceTriangles(triangle3ds, backCounter - 1, backCounter);
            else
                break;
    }
}
