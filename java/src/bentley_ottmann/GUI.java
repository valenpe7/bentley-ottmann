package bentley_ottmann;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame {

    private ArrayList<Segment> input_data;
    private ArrayList<Point> intersections;

    private double min_x, max_x, min_y, max_y;
    private boolean dragging = false;
    private int deltaX = 0;
    private int deltaY = 0;

    public GUI(final ArrayList<Segment> input_data, final ArrayList<Point> intersections) {

        this.input_data = input_data;
        this.intersections = intersections;
        this.find_limits();

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize( 800, 800);
        setTitle("Bentley-Ottmann algorithm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }

    private void find_limits() {
        this.min_x = Double.MIN_VALUE;
        this.max_x = Double.MAX_VALUE;
        this.min_y = Double.MIN_VALUE;
        this.max_y = Double.MAX_VALUE;
        for(Segment s : this.input_data) {
            if(s.first().get_x_coord() <= this.min_x) {
                this.min_x = s.first().get_x_coord();
            }
            if(s.first().get_x_coord() >= this.max_x) {
                this.max_x = s.first().get_x_coord();
            }
            if(s.first().get_y_coord() <= this.min_y) {
                this.min_x = s.first().get_x_coord();
            }
            if(s.first().get_y_coord() >= this.max_y) {
                this.max_x = s.first().get_x_coord();
            }
            if(s.second().get_x_coord() <= this.min_x) {
                this.min_x = s.first().get_x_coord();
            }
            if(s.second().get_x_coord() >= this.max_x) {
                this.max_x = s.first().get_x_coord();
            }
            if(s.second().get_y_coord() <= this.min_y) {
                this.min_x = s.first().get_x_coord();
            }
            if(s.second().get_y_coord() >= this.max_y) {
                this.max_x = s.first().get_x_coord();
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(Segment s : this.input_data) {
            g2.draw(new Line2D.Double(s.first().get_x_coord(), s.first().get_y_coord(), s.second().get_x_coord(), s.second().get_y_coord()));
        }
        for(Point p : this.intersections) {
            double new_x = p.get_x_coord() - 10 / 2.0;
            double new_y = p.get_y_coord() - 10 / 2.0;
            Ellipse2D.Double point = new Ellipse2D.Double(new_x, new_y, 10, 10);
            g2.setPaint(Color.RED);
            g2.fill(point);
            g2.draw(point);
        }

//        float dx = (getWidth() - 50) / (float) (csp.numCols + maxX), dy = (getHeight() - 50) / (float) (csp.numRows + maxY);
//        g2.drawString((actual + 1) + "/" + csp.solutions.size() + " [space to change]", 10, 35);
//
//        String[] line = csp.solutions.get(actual).split("\n");
//        for (int i = 0; i < csp.numCols; i++) {
//            for (int j = 0; j < csp.numRows; j++) {
//                Rectangle2D r2d = new Rectangle2D.Float(25 + (maxX) * dx + i * dx, 25 + (maxY) * dy + j * dy, dx, dy);
//                g.setColor(decodeColor(line[j].charAt(i)));
//                g2.fill(r2d);
//                g.setColor(Color.BLACK);
//            }
//        }
//
//        for (int i = 0; i < csp.numCols; i++) {
//            int mx = csp.rulesInCol[i].length;
//            for (int k = 0; k < mx; k++) {
//                Rectangle2D r2d = new Rectangle2D.Float(25 + (maxX) * dx + i * dx, 25 + (maxY - 1) * dy - k * dy, dx, dy);
//                g.setColor(decodeColor(csp.rulesInCol[i][mx - 1 - k].color));
//                g2.fill(r2d);
//                if (csp.rulesInCol[i][mx - 1 - k].color == 'B') {
//                    g.setColor(Color.WHITE);
//                } else {
//                    g.setColor(Color.BLACK);
//                }
//                g2.drawString("" + csp.rulesInCol[i][mx - 1 - k].length,
//                        (maxX) * dx + 35 + i * dx, (maxY - 1) * dy + 45 - k * dy);
//            }
//            g.setColor(Color.BLACK);
//            g2.draw(new Line2D.Float(25f + (i + maxX + 1) * dx, 25f + maxY * dy, 25 + (i + maxX + 1) * dx, getHeight() - 25f));
//        }
//
//        for (int i = 0; i < csp.numRows; i++) {
//            int mx = csp.rulesInRow[i].length;
//            for (int k = 0; k < mx; k++) {
//                Rectangle2D r2d = new Rectangle2D.Float(25 + (maxX - 1) * dx - k * dx, 25 + (maxY) * dy + i * dy, dx, dy);
//                g.setColor(decodeColor(csp.rulesInRow[i][mx - 1 - k].color));
//                g2.fill(r2d);
//                if (csp.rulesInRow[i][mx - 1 - k].color == 'B') {
//                    g.setColor(Color.WHITE);
//                } else {
//                    g.setColor(Color.BLACK);
//                }
//                g2.drawString("" + csp.rulesInRow[i][mx - 1 - k].length,
//                        (maxX - 1) * dx + 35 - k * dx, (maxY) * dy + 45 + i * dy);
//            }
//            g.setColor(Color.BLACK);
//            g2.draw(new Line2D.Float(25f + maxX * dx, 25 + (i + maxY + 1) * dy, getWidth() - 25f, 25 + (i + maxY + 1) * dy));
//        }
//        g.setColor(Color.black);
//        g2.setStroke(new BasicStroke(3));
//        g2.draw(new Line2D.Float(25f + (maxX) * dx, 25 + (maxY) * dy, getWidth() - 25f, 25 + (maxY) * dy));
//        g2.draw(new Line2D.Float(25 + (maxX) * dx, 25f + (maxY) * dy, 25 + (maxX) * dx, getHeight() - 25f));
//        g2.draw(new Line2D.Float(getWidth() - 25f, 25 + (maxY) * dy, getWidth() - 25f, getHeight() - 25f));
//        g2.draw(new Line2D.Float(25 + (maxX) * dx, getHeight() - 25f, getWidth() - 25f, getHeight() - 25f));
    }
}