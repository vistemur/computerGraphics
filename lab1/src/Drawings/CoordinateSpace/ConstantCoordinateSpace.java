package Drawings.CoordinateSpace;


public class ConstantCoordinateSpace  extends LinearCoordinateSpace {

    int width, height;

    public ConstantCoordinateSpace(int userMinX, int userMaxX, int userMinY, int userMaxY, int width, int height) {
        super(userMinX, userMaxX, userMinY, userMaxY);
        this.realMaxX = width;
        this.realMaxY = height;
        setSize(width, height);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setReal(int realMaxX, int realMaxY) {
        if (realMaxX > width) {
            this.realMinX = (realMaxX - width) / 2;
            this.realMaxX = realMaxX - this.realMinX;
        } else {
            this.realMinX = 0;
            this.realMaxX = width;
        }
        if (realMaxY > height) {
            this.realMinY = (realMaxY - height) / 2;
            this.realMaxY = realMaxY - this.realMinY;
        } else {
            this.realMinY = 0;
            this.realMaxY = height;
        }
    }
}
