package Drawings.graphicElements.Dimension3d;

import Drawings.graphicElements.Support.BuildingPoints;
import Drawings.graphicElements.Support.Point3d;

public class Surface extends Triangled3dElement {

    private int steps = 10;

    public void setData(Point3d ... points) {
        buildingPoints = new BuildingPoints(points);
        countTriangles();
    }

    private void countTriangles() {
        try {
            int c;
            float step = 1 / (float) steps;
            Triangle3d[] triangles = new Triangle3d[steps * steps * 2];
            Point3d[] prevLinePoints = new Point3d[steps + 1];
            Point3d prevPoint;
            Point3d newPoint;

            c = 0;
            for (float w = 0; w <= 1 + step / 10; w += step) {
                prevLinePoints[c] = countPoint(0, w);
                c++;
            }

            c = 0;
            for (float u = step; u <= 1 + step / 10; u += step) {
                prevPoint = countPoint(u, 0);
                int currentLinePointNumber = 1;
                for (float w = step; w <= 1 + step / 10; w += step) {

                    triangles[c] = new Triangle3d();
                    triangles[c].setPoints(prevLinePoints[currentLinePointNumber - 1], prevPoint, prevLinePoints[currentLinePointNumber]);
                    c++;

                    newPoint = countPoint(u, w);
                    triangles[c] = new Triangle3d();
                    triangles[c].setPoints(newPoint, prevLinePoints[currentLinePointNumber], prevPoint);

                    prevLinePoints[currentLinePointNumber - 1].set(prevPoint);
                    prevPoint.set(newPoint);

                    c++;
                    currentLinePointNumber++;
                }
                prevLinePoints[currentLinePointNumber - 1].set(prevPoint);
            }
            setTriangles(triangles);
        } catch (Exception e) {
            System.out.println("unable to set triangles");
        }
    }

    protected Point3d countPoint(float u, float w) {
        Point3d point;
        Point3d adder;

        point = new Point3d();

        adder = buildingPoints.getPoint(0);
        adder.mul((1 - u) * (1 - w));
        point.add(adder);

        adder = buildingPoints.getPoint(1);
        adder.mul((1 - u) * w);
        point.add(adder);

        adder = buildingPoints.getPoint(2);
        adder.mul(u * (1 - w));
        point.add(adder);

        adder = buildingPoints.getPoint(3);
        adder.mul(u * w);
        point.add(adder);

        return point;
    }

    protected void countCenter() {
        center = buildingPoints.getCenter();
        centerIsSet = true;
    }

    public void setSteps(int steps) {
        this.steps = steps;
        countTriangles();
    }

    protected boolean isNear(float number, float near) {
        float precision = 1.0f / (steps * 10);
        return  (number >= (near - precision)) && (number <= (near + precision));
    }
}
