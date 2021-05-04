package Drawings.graphicElements.Support;

public class Point3d extends Point {
    public float z;

    public Point3d(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    public Point3d() {
        super();
        this.z = 0;
    }

    public Point3d(Point3d point) {
        super(point);
        this.z = point.z;
    }

    public String toString() {
        return "(" + x + "," + y + ", " + z + ")";
    }

    public void add(Point3d point) {
        super.add(point);
        this.z += point.z;
    }

    public void add(float ... plusers) {
        super.add(plusers);
        if (plusers.length > 2)
            this.z += plusers[2];
        else if (plusers.length == 1)
            this.z += plusers[0];
    }

    public void mul(float ... multik) {
        super.mul(multik);
        if (multik.length > 2)
            this.z *= multik[2];
        else if (multik.length == 1)
            this.z *= multik[0];
    }

    public void del(float ... delik) {
        super.del(delik);
        if (delik.length > 2)
            this.z /= delik[2];
        else if (delik.length == 1)
            this.z /= delik[0];
    }

    public Point3d copy() {
        return new Point3d(x, y, z);
    }

    public void set(Point3d point) {
        super.set(point);
        this.z = point.z;
    }
}
