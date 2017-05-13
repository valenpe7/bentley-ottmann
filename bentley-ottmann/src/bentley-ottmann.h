#pragma once
#include <queue>
#include <vector>
#include <set>
#include <functional>

#include "segment.h"

struct comparator {
	bool operator()(segment& s_1, segment& s_2) const {
		if(s_1.get_limits()[2] > s_2.get_limits()[2]) {
			return true;
		}
		if(s_1.get_limits()[2] < s_2.get_limits()[2]) {
			return false;
		}
		if(s_1.get_limits()[2] == s_2.get_limits()[2]) {
			if(s_1.get_limits()[0] <= s_2.get_limits()[0]) {
				return true;
			}
			if(s_1.get_limits()[0] > s_2.get_limits()[0]) {
				return false;
			}
		}
		return true;
	}
};

class bentley_ottmann {
private:
	std::vector<segment> input_data;
	std::priority_queue<segment, std::vector<segment>, comparator> Q;
	std::set<segment> T;
public:
	bentley_ottmann(std::vector<segment> _input_data);
	void find_intersections();
	~bentley_ottmann();
};