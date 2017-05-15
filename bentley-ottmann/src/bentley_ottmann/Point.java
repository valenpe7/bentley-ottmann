package bentley_ottmann;

/**
 * Created by valen_000 on 14. 5. 2017.
 */

public class Point {

    private double x_coord;
    private double y_coord;

    Point(double x, double y) {
        this.x_coord = x;
        this.y_coord = y;
    }

    public double get_x_coord() {
        return this.x_coord;
    }

    public void set_x_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public double get_y_coord() {
        return this.y_coord;
    }

    public void set_y_coord(double y_coord) {
        this.y_coord = y_coord;
    }

}
