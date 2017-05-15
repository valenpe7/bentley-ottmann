#pragma once

class point {
private:
	double x_coord;
	double y_coord;
public:
	point(double _x_coord, double _y_coord);
	double get_x_coord() const;
	double get_y_coord() const;
	~point();
};
