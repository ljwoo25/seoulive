package org.zerock.seoulive.board.course.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CourseSearchTO {
	private String searchType;
	private String keyword;
	
} // end class
