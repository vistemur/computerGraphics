package Drawings.graphicElements.Dimension3d.Visibility;

import Drawings.graphicElements.Dimension3d.Triangle3d;

public abstract class VisibilityController {
    public abstract void SetVisible(Triangle3d[] triangle3ds);

    protected void replaceTriangles(Triangle3d[] triangle3ds, int pos1, int pos2) {
        if (pos1 < triangle3ds.length && pos2 < triangle3ds.length) {
            Triangle3d replacer = triangle3ds[pos1];
            triangle3ds[pos1] = triangle3ds[pos2];
            triangle3ds[pos2] = replacer;
        } else {
            System.out.print("unable to replace triangles " + pos1 + " and " + pos2);
        }
    }
}
