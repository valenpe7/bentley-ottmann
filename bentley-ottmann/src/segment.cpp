#include "segment.h"

segment::segment(point p_1, point p_2) :
	endpoints(p_1, p_2) {
	//if(this->endpoints.first.get_x_coord() <= this->endpoints.second.get_x_coord()) {
	//	this->limits[0] = this->endpoints.first.get_x_coord();
	//	this->limits[1] = this->endpoints.second.get_x_coord();
	//} else {
	//	this->limits[0] = this->endpoints.second.get_x_coord();
	//	this->limits[1] = this->endpoints.first.get_x_coord();
	//}
	//if(this->endpoints.first.get_y_coord() <= this->endpoints.second.get_y_coord()) {
	//	this->limits[2] = this->endpoints.first.get_y_coord();
	//	this->limits[3] = this->endpoints.second.get_y_coord();
	//} else {
	//	this->limits[2] = this->endpoints.second.get_y_coord();
	//	this->limits[3] = this->endpoints.first.get_y_coord();
	//}
}

std::pair<point, point> segment::get_endpoints() {
	return this->endpoints;
}

//std::array<double, 4> segment::get_limits() {
//	return this->limits;
//}

segment::~segment() {
}