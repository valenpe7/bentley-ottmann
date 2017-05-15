#include "bentley-ottmann.h"
#include "segment.h"

#include <iostream>

bentley_ottmann::bentley_ottmann(std::vector<segment> _input_data) {
	this->input_data = _input_data;
	for(size_t i = 0; i < this->input_data.size(); i++) {
		segment s = this->input_data[i];
		point p_1 = s.get_endpoints().first;
		point p_2 = s.get_endpoints().second;
		if(p_1.get_x_coord() <= p_2.get_x_coord()) {
			this->Q.push(event(p_1, s, 0));
			this->Q.push(event(p_2, s, 1));
		} else {
			this->Q.push(event(p_1, s, 1));
			this->Q.push(event(p_2, s, 2));
		}
	}
}

void bentley_ottmann::find_intersections() {
	while(!this->Q.empty()) {
		event e = this->Q.top();
		std::cout << T.size() << std::endl;
		this->Q.pop();
		if(e.get_type() == 0) {
			auto result = this->T.insert(e.get_segment());
		}
		if(e.get_type() == 1) {
			auto result = this->T.erase(e.get_segment());
		}
		if(e.get_type() == 2) {
			this->intersections.push_back(e.get_point());
		}
	}
	return;
}

bentley_ottmann::~bentley_ottmann() {
}

bool operator< (segment& s_1, segment& s_2) {
	return s_1.get_endpoints().first.get_y_coord() < s_2.get_endpoints().second.get_y_coord();
}
