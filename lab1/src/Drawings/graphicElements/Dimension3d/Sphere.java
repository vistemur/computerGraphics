package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.Point3d;

public class Sphere extends Surface {

    private float radius;

    public void set(Point3d center, float radius) {
        setRadius(radius);
        setSteps(30);
        setCenter(center);
    }

    public void setCenter(Point3d center) {
        setData(center);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    protected Point3d countPoint(float u, float w) {
        Point3d point;
        float x, y, z;
        float sinU = (float) Math.sin(u * Math.PI * 2);
        x = sinU * (float)  Math.cos(w * Math.PI * 2);
        y = sinU * (float)  Math.sin(w * Math.PI * 2);
        z = (float) Math.cos(u * Math.PI * 2);
        point = new Point3d(radius * x, radius * y, radius * z);
        return point;
    }
}
