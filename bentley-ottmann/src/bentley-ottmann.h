#pragma once
#include <queue>
#include <vector>
#include <set>
#include <map>
#include <functional>

#include "segment.h"
#include "event.h"
#include <list>

struct x_compare {
	bool operator()(event e_1, event e_2) const {
		if(e_1.get_x_coord() > e_2.get_x_coord()) {
			return true;
		}
		if(e_1.get_x_coord() <= e_2.get_x_coord()) {
			return false;
		}
		return true;
	}
};

struct y_compare {
	bool operator()(segment s_1, segment s_2) const {
		if(s_1.get_endpoints().first.get_y_coord() > s_2.get_endpoints().second.get_y_coord()) {
			return true;
		}
		if(s_1.get_endpoints().first.get_y_coord() <= s_2.get_endpoints().second.get_y_coord()) {
			return false;
		}
		return true;
	}
};

class bentley_ottmann {
private:
	std::vector<segment> input_data;
	std::priority_queue<event, std::vector<event>, x_compare> Q;
	std::set<segment, y_compare> T;
	std::vector<point> intersections;
public:
	bentley_ottmann(std::vector<segment> _input_data);
	void find_intersections();
	~bentley_ottmann();
	friend bool operator< (segment& s_1, segment& s_2);
};