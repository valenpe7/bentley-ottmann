package bentley_ottmann;

import java.util.*;

/**
 * Created by valen_000 on 14. 5. 2017.
 */

public class Main {

    public static void main(String[] args) {

        double range_min = 100;
        double range_max = 900;

        ArrayList<Segment> data = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Point p_1 = new Point(rand(range_min, range_max), rand(range_min, range_max));
            Point p_2 = new Point(rand(range_min, range_max), rand(range_min, range_max));
            data.add(new Segment(p_1, p_2));
        }

        BentleyOttmann test = new BentleyOttmann(data);
        test.find_intersections();
        test.print_intersections();
        ArrayList<Point> intersections = test.get_intersections();

        new GUI(data, intersections);
    }

    private static double rand(double range_min, double range_max) {
        Random r = new Random();
        return range_min + (r.nextDouble() * (range_max - range_min));
    }

}
