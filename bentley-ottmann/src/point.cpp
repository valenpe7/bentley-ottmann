#include "point.h"

point::point(double _x_coord, double _y_coord) :
	x_coord(_x_coord),
	y_coord(_y_coord) {
}

double point::get_x_coord() {
	return this->x_coord;
}

double point::get_y_coord() {
	return this->y_coord;
}

point::~point() {
}


