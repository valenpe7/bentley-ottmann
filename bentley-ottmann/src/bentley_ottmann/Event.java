package bentley_ottmann;

import java.util.*;

/**
 * Created by valen_000 on 14. 5. 2017.
 */

public class Event {

    private Point point;
    private ArrayList<Segment> segments;
    private double value;
    private int type;

    Event(Point p, Segment s, int type) {
        this.point = p;
        this.segments = new ArrayList<>(Arrays.asList(s));
        this.value = p.get_x_coord();
        this.type = type;
    }

    Event(Point p, ArrayList<Segment> s, int type) {
        this.point = p;
        this.segments = s;
        this.value = p.get_x_coord();
        this.type = type;
    }

    public void add_point(Point p) {
        this.point = p;
    }

    public Point get_point() {
        return this.point;
    }

    public void add_segment(Segment s) {
        this.segments.add(s);
    }

    public ArrayList<Segment> get_segments() {
        return this.segments;
    }

    public void set_type(int type) {
        this.type = type;
    }

    public int get_type() {
        return this.type;
    }

    public void set_value(double value) {
        this.value = value;
    }

    public double get_value() {
        return this.value;
    }

}
