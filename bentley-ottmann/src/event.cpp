#include "event.h"

event::event(point _point, segment _segment, int _type) :
	p(_point), s(_segment), type(_type) {
}

double event::get_x_coord() const {
	return this->p.get_x_coord();
}

double event::get_y_coord() const {
	return this->p.get_y_coord();
}

int event::get_type() const {
	return this->type;
}

segment event::get_segment() const {
	return this->s;
}

point event::get_point() const {
	return this->p;
}



event::~event() {
	
}

