#pragma once

#include <utility>
#include <array>

#include "point.h"

class segment {
private:
	std::pair<point, point> endpoints;
	std::array<double, 4> limits;
public:
	segment(point p_1, point p_2);
	std::pair<point, point> get_endpoints();
	std::array<double, 4> get_limits();
	~segment();
};
