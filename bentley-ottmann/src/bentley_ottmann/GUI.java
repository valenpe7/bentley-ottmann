package bentley_ottmann;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Created by valen_000 on 15. 5. 2017.
 */

public class GUI extends JFrame {

    private ArrayList<Segment> input_data;
    private ArrayList<Point> intersections;
    private boolean repaint = false;

    public GUI(final ArrayList<Segment> input_data, final ArrayList<Point> intersections) {

        this.input_data = input_data;
        this.intersections = intersections;

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        setSize(1000, 1000);
        setTitle("Bentley-Ottmann algorithm");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint = !repaint;
                repaint();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        for(Segment s : this.input_data) {
            Line2D.Double segment = new Line2D.Double(s.first().get_x_coord(), s.first().get_y_coord(), s.second().get_x_coord(), s.second().get_y_coord());
            g2.draw(segment);
        }

        if(repaint) {
            g2.drawString("number of intersections: " + this.intersections.size(), 40, 70);
            for (Point p : this.intersections) {
                double new_x = p.get_x_coord() - 6 / 2.0;
                double new_y = p.get_y_coord() - 6 / 2.0;
                Ellipse2D.Double point = new Ellipse2D.Double(new_x, new_y, 6, 6);
                g2.setPaint(Color.RED);
                g2.fill(point);
                g2.draw(point);
            }
        }

    }
}