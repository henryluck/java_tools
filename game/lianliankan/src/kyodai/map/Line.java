package kyodai.map;

import java.awt.Point;

public class Line
{

    public Point a;
    public Point b;
    public int direct;

    public Line()
    {
    }

    public Line(int direct, Point a, Point b)
    {
        this.direct = direct;
        this.a = a;
        this.b = b;
    }
}
