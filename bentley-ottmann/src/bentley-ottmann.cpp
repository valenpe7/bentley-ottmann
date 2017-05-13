#include "bentley-ottmann.h"
#include "segment.h"

bentley_ottmann::bentley_ottmann(std::vector<segment> _input_data) {
	this->input_data = _input_data;
	for(size_t i = 0; i < this->input_data.size(); i++) {
		this->Q.push(this->input_data[i]);
	}
}

void bentley_ottmann::find_intersections() {
	while(!this->Q.empty()) {
		segment p = this->Q.top();
		this->Q.pop();
	}
	return;
}

bentley_ottmann::~bentley_ottmann() {
}

