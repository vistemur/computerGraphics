package Drawings.graphicElements.Support;

import Matrix.Matrix;

public class BuildingPoints {

    public float[][] points;

    public BuildingPoints(Point3d ... points) {
        setPoints(points);
    }

    public void setPoints(Point3d[] points) {
        this.points = new float[points.length][3];
        for (int point = 0; point < points.length; point++) {
            this.points[point][0] = points[point].x;
            this.points[point][1] = points[point].y;
            this.points[point][2] = points[point].z;
        }
    }

    public Point3d[] getPoints() {
        Point3d[] answer;

        answer = new Point3d[points.length];
        for (int pointNumber = 0; pointNumber < points.length; pointNumber++)
            answer[pointNumber] = getPoint(pointNumber);
        return answer;
    }

    public Point3d getPoint(int pointNumber) {
        Point3d point;

        point = new Point3d();
        try {
            point.x = this.points[pointNumber][0];
            point.y = this.points[pointNumber][1];
            point.z = this.points[pointNumber][2];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("unable to get point " + pointNumber);
            e.printStackTrace();
        }
        return point;
    }

    public Point3d getCenter() {
        Point3d center;

        center = new Point3d();
        try {
            for (int pointNumber = 0; pointNumber < points.length; pointNumber++)
                center.add(points[pointNumber][0], points[pointNumber][1], points[pointNumber][2]);
            center.del(points.length);
        } catch (Exception e) {
            System.out.println("unable to get center");
        }
        return center;
    }

    public void move(float x, float y, float z) {
        try {
            for (int point = 0; point < points.length; point++) {
                points[point][0] += x;
                points[point][1] += y;
                points[point][2] += z;
            }
        } catch (Exception e) {
            System.out.println("unable to move building points");
        }
    }

    public void rotate(float ... rotation) { // x, y, z
        Point3d center = getCenter();

        move(-center.x, -center.y, -center.z);
        if (rotation.length > 0)
            rotateX(rotation[0]);
        if (rotation.length > 1)
            rotateY(rotation[1]);
        if (rotation.length > 2)
            rotateZ(rotation[2]);
        move(center.x, center.y, center.z);
    }

    public void rotate(Point3d point, float ... rotation) { // x, y, z
        move(-point.x, -point.y, -point.z);
        if (rotation.length > 0)
            rotateX(rotation[0]);
        if (rotation.length > 1)
            rotateY(rotation[1]);
        if (rotation.length > 2)
            rotateZ(rotation[2]);
        move(point.x, point.y, point.z);
    }

    private void rotateX(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {1, 0                      , 0},
                        {0, (float) Math.cos(angle), (float) -Math.sin(angle)},
                        {0, (float) Math.sin(angle), (float) Math.cos(angle)}}));
    }

    private void rotateY(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {(float) Math.cos(angle) , 0, (float) Math.sin(angle)},
                        {0                       , 1, 0},
                        {(float) -Math.sin(angle), 0, (float) Math.cos(angle)}}));
    }

    private void rotateZ(float angle) {
        setMatrix(Matrix.multiply(getMatrix(), new float[][]
                {       {(float) Math.cos(angle), (float) -Math.sin(angle), 0},
                        {(float) Math.sin(angle), (float) Math.cos(angle) , 0},
                        {0,                       0                       , 1}}));
    }

    public void setMatrix(float[][] matrix) {
        if (matrix.length > 0 && matrix[0].length == 3) {
            points = matrix;
        } else {
            System.out.println("can't set matrix (wrong size)\nneeded (n, 3)\n given ");
        }
    }

    public float[][] getMatrix() {
        return points;
    }
}
