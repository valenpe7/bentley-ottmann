package bentley_ottmann;

import java.util.*;

/**
 * Created by valen_000 on 14. 5. 2017.
 */

public class BentleyOttmann {

    private Queue<Event> Q;
    private NavigableSet<Segment> T;
    private ArrayList<Point> X;

    BentleyOttmann(ArrayList<Segment> input_data) {
        this.Q = new PriorityQueue<>(new event_comparator());
        this.T = new TreeSet<>(new segment_comparator());
        this.X = new ArrayList<>();
        for(Segment s : input_data) {
            this.Q.add(new Event(s.first(), s, 0));
            this.Q.add(new Event(s.second(), s, 1));
        }
    }

    public void find_intersections() {
        while(!this.Q.isEmpty()) {
            Event e = this.Q.poll();
            double L = e.get_value();
            switch(e.get_type()) {
            case 0:
                for(Segment s : e.get_segments()) {
                    this.recalculate(L);
                    this.T.add(s);
                    if(this.T.lower(s) != null) {
                        Segment r = this.T.lower(s);
                        this.report_intersection(r, s, L);
                    }
                    if(this.T.higher(s) != null) {
                        Segment t = this.T.higher(s);
                        this.report_intersection(t, s, L);
                    }
                    if(this.T.lower(s) != null && this.T.higher(s) != null) {
                        Segment r = this.T.lower(s);
                        Segment t = this.T.higher(s);
                        this.remove_future(r, t);
                    }
                }
                break;
            case 1:
                for(Segment s : e.get_segments()) {
                    if(this.T.lower(s) != null && this.T.higher(s) != null) {
                        Segment r = this.T.lower(s);
                        Segment t = this.T.higher(s);
                        this.report_intersection(r, t, L);
                    }
                    this.T.remove(s);
                }
                break;
            case 2:
                Segment s_1 = e.get_segments().get(0);
                Segment s_2 = e.get_segments().get(1);
                this.swap(s_1, s_2);
                if(s_1.get_value() < s_2.get_value()) {
                    if(this.T.higher(s_1) != null) {
                        Segment t = this.T.higher(s_1);
                        this.report_intersection(t, s_1, L);
                        this.remove_future(t, s_2);
                    }
                    if(this.T.lower(s_2) != null) {
                        Segment r = this.T.lower(s_2);
                        this.report_intersection(r, s_2, L);
                        this.remove_future(r, s_1);
                    }
                } else {
                    if(this.T.higher(s_2) != null) {
                        Segment t = this.T.higher(s_2);
                        this.report_intersection(t, s_2, L);
                        this.remove_future(t, s_1);
                    }
                    if(this.T.lower(s_1) != null) {
                        Segment r = this.T.lower(s_1);
                        this.report_intersection(r, s_1, L);
                        this.remove_future(r, s_2);
                    }
                }
                this.X.add(e.get_point());
                break;
            }
        }
    }

    private boolean report_intersection(Segment s_1, Segment s_2, double L) {
        double x1 = s_1.first().get_x_coord();
        double y1 = s_1.first().get_y_coord();
        double x2 = s_1.second().get_x_coord();
        double y2 = s_1.second().get_y_coord();
        double x3 = s_2.first().get_x_coord();
        double y3 = s_2.first().get_y_coord();
        double x4 = s_2.second().get_x_coord();
        double y4 = s_2.second().get_y_coord();
        double r = (x2 - x1) * (y4 - y3) - (y2 - y1) * (x4 - x3);
        if(r != 0) {
            double t = ((x3 - x1) * (y4 - y3) - (y3 - y1) * (x4 - x3)) / r;
            double u = ((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) / r;
            if(t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                double x_c = x1 + t * (x2 - x1);
                double y_c = y1 + t * (y2 - y1);
                if(x_c > L) {
                    this.Q.add(new Event(new Point(x_c, y_c), new ArrayList<>(Arrays.asList(s_1, s_2)), 2));
                    return true;
                }
            }
        }
        return false;
    }

    private boolean remove_future(Segment s_1, Segment s_2) {
        for(Event e : this.Q) {
            if(e.get_type() == 2) {
                if((e.get_segments().get(0) == s_1 && e.get_segments().get(1) == s_2) || (e.get_segments().get(0) == s_2 && e.get_segments().get(1) == s_1)) {
                    this.Q.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    private void swap(Segment s_1, Segment s_2) {
        this.T.remove(s_1);
        this.T.remove(s_2);
        double value = s_1.get_value();
        s_1.set_value(s_2.get_value());
        s_2.set_value(value);
        this.T.add(s_1);
        this.T.add(s_2);
    }

    private void recalculate(double L) {
        Iterator<Segment> iter = this.T.iterator();
        while(iter.hasNext()) {
            iter.next().calculate_value(L);
        }
    }

    public void print_intersections() {
        for(Point p : this.X) {
            System.out.println("(" + p.get_x_coord() + ", " + p.get_y_coord() + ")");
        }
    }

    public ArrayList<Point> get_intersections() {
        return this.X;
    }

    private class event_comparator implements Comparator<Event> {
        @Override
        public int compare(Event e_1, Event e_2) {
            if(e_1.get_value() > e_2.get_value()) {
                return 1;
            }
            if(e_1.get_value() < e_2.get_value()) {
                return -1;
            }
            return 0;
        }
    }

    private class segment_comparator implements Comparator<Segment> {
        @Override
        public int compare(Segment s_1, Segment s_2) {
            if(s_1.get_value() < s_2.get_value()) {
                return 1;
            }
            if(s_1.get_value() > s_2.get_value()) {
                return -1;
            }
            return 0;
        }
    }
}