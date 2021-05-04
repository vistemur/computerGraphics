package windows.Exercises.lab3.Exercise3_1;

import Drawings.graphicElements.Support.Point3d;

public class SurfaceDataController {

    private Exercise3_1.PointsPanelDataSetter pointsPanelDataSetter;
    private Exercise3_1.SketchPointsDataSetter sketchPointsDataSetter;

    public SurfaceDataController(Exercise3_1.PointsPanelDataSetter pointsPanelDataSetter, Exercise3_1.SketchPointsDataSetter sketchPointsDataSetter) {
        this.pointsPanelDataSetter = pointsPanelDataSetter;
        this.sketchPointsDataSetter = sketchPointsDataSetter;
    }

    public void setPoints(Point3d[] points) {
        pointsPanelDataSetter.setPointsData(points);
        sketchPointsDataSetter.setPointsData(points);
    }

    public void setPointsFromSketch(Point3d[] points) {
        pointsPanelDataSetter.setPointsData(points);
    }

    public void setPointsFromPanel() {
        sketchPointsDataSetter.setPointsData(pointsPanelDataSetter.getPoints());
    }

    public void setPause(boolean pause) {
        sketchPointsDataSetter.setPause(pause);
    }
}
