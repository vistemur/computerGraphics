package Drawings.graphicElements.Dimension3d.Visibility;

import Drawings.graphicElements.Dimension3d.Triangle3d;

public class Normaler extends VisibilityController {
    public void SetVisible(Triangle3d[] triangle3ds) {
        for (var triangle : triangle3ds)
            triangle.setVisible(triangle.getNormal().z <= 0);
    }
}
