package Drawings.graphicElements.Support;

public class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public static float length(Point point1, Point point2) {
        return (float) Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public void add(Point point) {
        this.x += point.x;
        this.y += point.y;
    }

    public void add(float ... plusers) {
        if (plusers.length == 1) {
            this.x += plusers[0];
            this.y += plusers[0];
        } else if (plusers.length > 1) {
            this.x += plusers[0];
            this.y += plusers[1];
        }
    }

    public void mul(float ... multik) {
        if (multik.length == 1) {
            this.x *= multik[0];
            this.y *= multik[0];
        } else if (multik.length > 1) {
            this.x *= multik[0];
            this.y *= multik[1];
        }
    }

    public void del(float ... delik) {
        if (delik.length == 1) {
            this.x /= delik[0];
            this.y /= delik[0];
        } else if (delik.length > 1) {
            this.x /= delik[0];
            this.y /= delik[1];
        }
    }

    public Point copy() {
        return new Point(x, y);
    }

    public void set(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
}
