package Models;


public class Line {
    private Point point1;
    private Point point2;
    private double b; // y = ax+b (intercepted part)

    public Line(){
        point1 = new Point();
        point2 = new Point();
    }
    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint2() {
        return point2;
    }

}
