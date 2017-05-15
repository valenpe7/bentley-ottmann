#pragma once

#include "segment.h"
#include "point.h"

class event {
public:
	point p;
	segment s;
	int type;
public:
	event(point _point, segment _segment, int _type);
	double get_x_coord() const;
	double get_y_coord() const;
	int get_type() const;
	segment get_segment() const;
	point get_point() const;
	~event();
};
