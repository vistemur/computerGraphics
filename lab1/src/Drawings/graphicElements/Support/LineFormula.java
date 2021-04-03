package Drawings.graphicElements.Support;

import Drawings.graphicElements.Line;

import java.awt.*;

public class LineFormula {

    public double a;  // a*x + b*y + c = 0
    public double b = 1;
    public double c;

    public LineFormula(double ... numbers) {
        if (numbers.length > 0) {
            a = numbers[0];
            if (numbers.length > 1) {
                b = numbers[1];
                if (numbers.length > 2)
                    c = numbers[2];
            }
        }
    }

    public LineFormula(Point point1, Point point2) {
        set(point1, point2);
    }

    public void makePerpendicular(Point through) {
        if (b == 0) {
            a = 0;
            b = 1;
            c = -through.y;
        }else if (a == 0) {
            a = -1;
            b = 0;
            c = through.x;
        } else {
            c = b;
            b = -a;
            a = c;
            c = through.x - countX(through.y);
        }
    }

    public LineFormula copy() {
        return new LineFormula(a, b, c);
    }

    public boolean isEqual(LineFormula line) {
        return isParallel(line) && line.c == c;
    }

    public boolean isParallel(LineFormula line) {
        return a == line.a && b == line.b;
    }

    public Point getIntersectionPoint(LineFormula line) {
        LineFormula helperFormula;
        double x, y;

        if (isParallel(line))
            return new Point();
        helperFormula = copy();
        helperFormula.multiply(-line.a);
        if (helperFormula.a == line.a) {
            helperFormula.a = 0;
            helperFormula.b -= line.b;
            helperFormula.c -= line.c;
            y = -(helperFormula.c / helperFormula.b);
            if (line.a == 0)
                x = countX(y);
            else
                x = line.countX(y);
        } else {
            x = -line.c;
            y = countY(x);
        }
        return new Point((int) x, (int) y);
    }

    public void set(Point point1, Point point2) {
        if (point2.x == point1.x) {
            a = 1;
            b = 0;
            c = -point1.x;
        } else {
            a = ((double) point2.y - point1.y) / (point2.x - point1.x);
            b = -1;
            c = point1.y - point1.x * a;
        }
    }

    public void multiply(double multiplier) {
        a *= multiplier;
        b *= multiplier;
        c *= multiplier;
    }

    public double countX(double y) {
        if (a == 0)
            return 0;
        return -(y * b + c) / a;
    }

    public double countY(double x) {
        if (b == 0)
            return 0;
        return -(x * a + c) / b;
    }

    public String toString() {
        return a + " * x " + nbToStr(b) + " * y " + nbToStr(c) + " = 0";
    }

    private String nbToStr(double nb) {
        if (nb >= 0)
            return "+ " + nb;
        else
            return "- " + nb * -1;
    }
}
