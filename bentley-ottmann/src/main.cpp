#include <iostream>

#include "bentley-ottmann.h"
#include "segment.h"
#include "point.h"

void main(int argc, char** argv) {

	std::vector<segment> data;
	data.push_back(segment(point(-1.0, -1.0), point(0.5, 1.0)));
	data.push_back(segment(point(-0.5, 1.0), point(1.0, -1.0)));

	bentley_ottmann test(data);
	test.find_intersections();

	return;
}
