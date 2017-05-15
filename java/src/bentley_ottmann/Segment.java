package bentley_ottmann;

/**
 * Created by valen_000 on 14. 5. 2017.
 */

public class Segment {

    private Point p_1;
    private Point p_2;
    double value;

    Segment(Point p_1, Point p_2) {
        this.p_1 = p_1;
        this.p_2 = p_2;
        this.set_value(this.first().get_y_coord());
    }

    public Point first() {
        if(p_1.get_x_coord() <= p_2.get_x_coord()) {
            return p_1;
        } else {
            return p_2;
        }
    }

    public Point second() {
        if(p_1.get_x_coord() <= p_2.get_x_coord()) {
            return p_2;
        } else {
            return p_1;
        }
    }

    public void set_value(double value) {
        this.value = value;
    }

    public double get_value() {
        return this.value;
    }

}
